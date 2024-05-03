package com.uteev.convertorusdtobtc.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uteev.convertorusdtobtc.R


class CoinPriceListActivity : AppCompatActivity() {
    private lateinit var coinViewModel: CoinViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        coinViewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        coinViewModel.priceList.observe(this, Observer {
            Log.d("Success activity", it.toString())
        })
        coinViewModel.getDetailInfo("BTC").observe(this, Observer {
            Log.d("Success activity getDetailInfo", it.toString())
        })
    }

}