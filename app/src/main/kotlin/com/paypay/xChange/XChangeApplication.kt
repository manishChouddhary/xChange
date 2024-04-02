package com.paypay.xChange

import android.app.Application
import androidx.room.Room
import com.paypay.xChange.core.data.local.AppDatabase
import com.paypay.xChange.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

const val BASE_URL_KEY = "base_url_key"
const val API_AUTH_KEY = "api_auth_key"
const val DATABASE_NAME = "currency_exchange_database"

class XChangeApplication : Application() {
    private val database by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@XChangeApplication)
            properties(
                mapOf(
                    BASE_URL_KEY to BuildConfig.base_url,
                    API_AUTH_KEY to BuildConfig.api_auth,
                    DATABASE_NAME to database
                )
            )
            modules(appModule)
        }
    }
}