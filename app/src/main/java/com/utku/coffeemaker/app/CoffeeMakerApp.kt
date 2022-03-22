package com.utku.coffeemaker.app

import android.app.Application
import com.utku.coffeemaker.di.viewModelModule
import com.utku.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CoffeeMakerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoffeeMakerApp)
            androidLogger(Level.ERROR)
            modules(dataModule + viewModelModule)
        }
    }
}