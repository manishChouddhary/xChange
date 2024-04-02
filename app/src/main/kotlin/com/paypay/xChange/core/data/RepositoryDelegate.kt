package com.paypay.xChange.core.data

import com.paypay.xChange.core.model.Conversion
import com.paypay.xChange.core.model.Currency
import kotlinx.coroutines.flow.Flow

interface RepositoryDelegate {

    fun getCurrencies():Flow<List<Currency>>

    fun getConversion(conversion: Conversion): Flow<Conversion>
}