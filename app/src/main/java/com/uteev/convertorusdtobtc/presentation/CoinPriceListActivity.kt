package com.uteev.convertorusdtobtc.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uteev.convertorusdtobtc.R


class CoinPriceListActivity : AppCompatActivity() {
    private lateinit var coinViewModel: CoinViewModel
    private lateinit var coinListAdapter: CoinListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        setupRecyclerView()
        coinViewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        coinViewModel.priceList.observe(this, Observer {
            Log.d("Success activity", it.toString())
            coinListAdapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_list_coin_info)
        installRv(rvShopList)
    }

    private fun installRv(rvShopList: RecyclerView) {
        with(rvShopList) {
            layoutManager = GridLayoutManager(this@CoinPriceListActivity, 1)
            coinListAdapter = CoinListAdapter()
            adapter = coinListAdapter
            Log.d("Success adapter", coinListAdapter.toString())
        }
    }

}