package com.paypay.xChange.core.data.remote.model

data class ExchangeRatesResponse(
    val base: String,
    val timestamp: Int,
    val rates: Map<String, Double>
)