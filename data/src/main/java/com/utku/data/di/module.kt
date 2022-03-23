package com.utku.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.utku.data.remote.CoffeeMakerService
import com.utku.data.remote.RemoteDataSource
import com.utku.data.repository.CoffeeMakerRepository
import com.utku.data.util.BASE_URL
import com.utku.data.util.NoConnectionInterceptor
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalSerializationApi::class)
val dataModule = module {

    /**
     * This is kotlin serialization, use for convert json to data class
     * or data class to json
     * */

    single {
        Json {
            prettyPrint = true
            isLenient = true
            coerceInputValues = true
            ignoreUnknownKeys = true
        }
    }

    single { NoConnectionInterceptor(androidApplication()) }

    /**
     * Http client to connect API
     * All TimeOuts 20 seconds
     * Uses [HttpLoggingInterceptor] to log responses or requests
     * */

    single {
        OkHttpClient.Builder()
            .writeTimeout(20L, TimeUnit.SECONDS)
            .readTimeout(20L, TimeUnit.SECONDS)
            .connectTimeout(20L, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(get() as NoConnectionInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory((get() as Json).asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single { (get() as Retrofit).create(CoffeeMakerService::class.java) }

    single { RemoteDataSource(get()) }

    single { CoffeeMakerRepository(get()) }

}