package com.android.akesosdkapp

import android.app.Application
import com.android.akesosdkapp.di.databaseModule
import com.android.akesosdkapp.di.networkModule
import com.chintansoni.android.repositorypattern.BuildConfig
import com.android.akesosdkapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeTimber()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(networkModule, databaseModule, viewModelModule))
        }
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}