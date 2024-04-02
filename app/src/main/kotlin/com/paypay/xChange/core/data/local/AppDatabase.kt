package com.paypay.xChange.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paypay.xChange.core.model.Currency

@Database(entities = [Currency::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}