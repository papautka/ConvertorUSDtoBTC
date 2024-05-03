package com.uteev.convertorusdtobtc.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uteev.convertorusdtobtc.R

class CoinItemViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
    val iLogoCoin = view.findViewById<ImageView>(R.id.iLogoCoin)
    val tvFsTs = view.findViewById<TextView>(R.id.tvFsTs)
    val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
    val tvTimeLastUpdate = view.findViewById<TextView>(R.id.tvTimeLastUpdate)
}