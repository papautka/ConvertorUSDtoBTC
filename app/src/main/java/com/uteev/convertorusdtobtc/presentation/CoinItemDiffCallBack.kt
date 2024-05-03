package com.uteev.convertorusdtobtc.presentation

import androidx.recyclerview.widget.DiffUtil
import com.uteev.convertorusdtobtc.domain.pojo.coinprice.CoinPriceInfo

class CoinItemDiffCallBack : DiffUtil.ItemCallback<CoinPriceInfo>() {
    override fun areItemsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun areContentsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Any? {
        return super.getChangePayload(oldItem, newItem)
    }
}