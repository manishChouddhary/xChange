package com.paypay.xChange.core.data.remote

import com.paypay.xChange.core.data.remote.model.ExchangeRatesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ExchangeService {
    @GET("/currencies.json")
    suspend fun getCurrencies(): Response<Map<String,String>>

    @GET("/latest.json")
    suspend fun getCurrencyRate():Response<ExchangeRatesResponse>
}