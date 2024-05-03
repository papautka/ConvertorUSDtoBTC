package com.uteev.convertorusdtobtc.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.uteev.convertorusdtobtc.R


class MainActivity : AppCompatActivity() {
    private lateinit var coinViewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coinViewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        coinViewModel.loadData()
    }

}