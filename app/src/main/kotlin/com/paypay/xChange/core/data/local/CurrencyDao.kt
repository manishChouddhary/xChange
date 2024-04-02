package com.paypay.xChange.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.paypay.xChange.core.model.Currency

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<Currency>)

    @Query("SELECT * FROM currency")
    fun getAllCurrencies(): List<Currency>

    @Update
    suspend fun updateCurrencyRates(currencies: List<Currency>)
}