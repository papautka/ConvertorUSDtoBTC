package com.uteev.convertorusdtobtc.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.uteev.convertorusdtobtc.data.apiservice.ApiFactory
import com.uteev.convertorusdtobtc.data.database.AppDatabase
import com.uteev.convertorusdtobtc.domain.pojo.coinprice.CoinPriceInfo
import com.uteev.convertorusdtobtc.domain.pojo.coinprice.CoinPriceInfoRawData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    val priceList = db.coinPriceInfoDao().getPriceList()

    private val compositeDisposable = CompositeDisposable()

    fun loadData() {
        val disposbale = ApiFactory.apiService.getTopCoinsInfo()
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fromSymbol = it) }
            .map { getPriceListFromRawData(it) }
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("SUCCESS_OF_LOADING_DATA", it.toString())
                Thread {
                    db.coinPriceInfoDao().insertPriceList(it)
                }.start()
            },{
                Log.d("FAIL_OF_LOADING_DATA", it.message.toString())
            })
        compositeDisposable.add(disposbale)
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
    ) : List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()

        // Получаем JsonObject из CoinPriceInfoRawData
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject
        if (jsonObject == null) {
            return result
        }
        // Получаем KeySet из JsonObject (Каждая пара криптовалюты имеет свой уникальный Key)
        val coinKeySet = jsonObject.keySet()

        // Перебираем все пары криптовалют и получаем CoinPriceInfo
        for(key in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(key)
            val currencyKeySet = currencyJson.keySet()

            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}