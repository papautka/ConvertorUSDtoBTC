package com.uteev.convertorusdtobtc.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import com.uteev.convertorusdtobtc.R
import com.uteev.convertorusdtobtc.domain.pojo.coininfo.ListOfDatum
import com.uteev.convertorusdtobtc.domain.pojo.coinprice.CoinPriceInfo

class CoinListAdapter : ListAdapter<CoinPriceInfo, CoinItemViewHolder>(CoinItemDiffCallBack()) {

    var onCoinItemClick : ((CoinPriceInfo) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {
        val layout = R.layout.item_coin
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return CoinItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CoinItemViewHolder, position: Int) {
        val item = getItem(position)

        viewHolder.view.setOnClickListener {
            onCoinItemClick?.invoke(item)
        }
        viewHolder.tvFsTs.text = item.fromSymbol + " / " + item.toSymbol
        viewHolder.tvPrice.text = item.price.toString()
        viewHolder.tvTimeLastUpdate.text = item.getFormattedTime()
        Picasso.get().load(item.getFullImageUrl()).into(viewHolder.iLogoCoin)
    }

    companion object {
        const val MAX_SIZE_POOL = 15
    }

}