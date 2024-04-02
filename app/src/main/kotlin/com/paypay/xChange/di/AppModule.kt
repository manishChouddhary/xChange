package com.paypay.xChange.di

import android.content.Context
import androidx.room.Room
import com.paypay.xChange.API_AUTH_KEY
import com.paypay.xChange.BASE_URL_KEY
import com.paypay.xChange.DATABASE_NAME
import com.paypay.xChange.core.data.Repository
import com.paypay.xChange.core.data.RepositoryDelegate
import com.paypay.xChange.core.data.local.AppDatabase
import com.paypay.xChange.core.data.local.LocalDataRepository
import com.paypay.xChange.core.data.remote.ExchangeService
import com.paypay.xChange.core.data.remote.RemoteDataRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { createAppDatabase(androidContext()) }
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single {
        val baseUrl = getProperty<String>(BASE_URL_KEY)
        val apiAuth = getProperty<String>(API_AUTH_KEY)
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(createOkHttpClient(apiAuth))
            .build()
    }

    single {
        get<Retrofit>().create(ExchangeService::class.java)
    }

    factory { LocalDataRepository(get()) }
    factory { RemoteDataRepository(get()) }
    factory<RepositoryDelegate> { Repository(get(), get()) }
}

private fun createOkHttpClient(apiAuth: String): OkHttpClient {
    val timeout = 30_000L
    return OkHttpClient.Builder()
        .connectTimeout(timeout, TimeUnit.MILLISECONDS)
        .readTimeout(timeout, TimeUnit.MILLISECONDS)
        .addInterceptor { chain ->
            val original = chain.request()
            val url = original.url().newBuilder()
                .addQueryParameter("api_id", apiAuth)
                .build()
            val request = original.newBuilder().url(url).build()
            chain.proceed(request)
        }
        .build()
}

private fun createAppDatabase(context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
        .build()