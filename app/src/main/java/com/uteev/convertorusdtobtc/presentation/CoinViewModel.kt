package com.uteev.convertorusdtobtc.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.uteev.convertorusdtobtc.data.apiservice.ApiFactory
import com.uteev.convertorusdtobtc.data.database.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    val priceList = db.coinPriceInfoDao().getPriceList()

    private val compositeDisposable = CompositeDisposable()

    fun loadData() {
        val disposbale = ApiFactory.apiService.getTopCoinsInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("SUCCESS_OF_LOADING_DATA", it.toString())
            },{
                Log.d("FAIL_OF_LOADING_DATA", it.message.toString())
            })
        compositeDisposable.add(disposbale)
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}