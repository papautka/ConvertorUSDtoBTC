package com.uteev.convertorusdtobtc.data

import com.uteev.convertorusdtobtc.domain.pojo.coininfo.ListOfDatum
import com.uteev.convertorusdtobtc.domain.pojo.coinprice.CoinPriceInfo
import com.uteev.convertorusdtobtc.domain.pojo.coinprice.CoinPriceInfoRawData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) toSymbol: String = CURRENCY_USD
    ): Single<ListOfDatum>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_FROM_SYMBOL) fromSymbol: String,
        @Query(QUERY_PARAM_TO_SYMBOL) toSymbol: String = CURRENCY_USD
    ) : Single<CoinPriceInfoRawData>
    companion object {
        const val QUERY_PARAM_API_KEY = "api_key"
        const val QUERY_PARAM_LIMIT = "limit"
        const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val CURRENCY_USD = "USD"
        private const val CURRENCY_BTC = "BTC"
        const val QUERY_PARAM_FROM_SYMBOL = "fsym"
    }
}