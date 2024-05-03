package com.uteev.convertorusdtobtc.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uteev.convertorusdtobtc.R

class CountItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.count_item_activity)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, CountItemActivity::class.java)
            return intent
        }
    }
}