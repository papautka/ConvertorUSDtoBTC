package com.uteev.convertorusdtobtc.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.uteev.convertorusdtobtc.R
import com.uteev.convertorusdtobtc.data.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getTopCoinsInfo()
        getFullPriceList()
    }

    private fun getTopCoinsInfo() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TEST_TOP_COINS", it.toString())
            },{
                Log.d("TEST_OF_LOADING_DATA", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    private fun getFullPriceList() {
        val disponsable = ApiFactory.apiService.getFullPriceList(
            fromSymbol = "BTC,EOS,ETH"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TEST_FULL_PRICE_LIST", it.toString())
            },{
                Log.d("TEST_OF_LOADING_DATA", it.message.toString())
            })
        compositeDisposable.add(disponsable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}