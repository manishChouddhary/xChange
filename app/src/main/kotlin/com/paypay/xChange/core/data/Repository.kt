package com.paypay.xChange.core.data

import com.paypay.xChange.core.data.local.LocalDataRepository
import com.paypay.xChange.core.data.remote.RemoteDataRepository
import com.paypay.xChange.core.model.Conversion
import com.paypay.xChange.core.model.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class Repository(
    private val local: LocalDataRepository,
    private val remote: RemoteDataRepository
) : RepositoryDelegate {
    override fun getCurrencies():  Flow<List<Currency>> = flow {
        try {
            val currencies = remote.getCurrencies()
            val rates = remote.getCurrencyRates()

            val currency = currencies.map { entry ->
                Currency(
                    code = entry.code,
                    name = entry.name,
                    rate = rates.find { it.code == entry.code }?.rate ?: 0.0
                )
            }
            local.updateCurrencies(currency)
            emit(local.getCurrencies())
        } catch (e: Exception) {
            emit(listOf())
        }
    }.catch { e ->
        emit(listOf())
    }.onStart {
        emit(listOf())
    }

    override fun getConversion(conversion: Conversion): Flow<Conversion> {
        TODO("Not yet implemented")
    }
}