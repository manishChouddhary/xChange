package com.paypay.xChange.core.data.local

import com.paypay.xChange.core.model.Currency

class LocalDataRepository(private val db: AppDatabase) {
    suspend fun updateCurrencies(currencies :List<Currency>){
        db.currencyDao().insertCurrencies(currencies)
    }

    fun getCurrencies() = db.currencyDao().getAllCurrencies()

}