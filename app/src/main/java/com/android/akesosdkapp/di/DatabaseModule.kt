package com.android.akesosdkapp.di

import androidx.room.Room
import com.android.akesosdkapp.model.local.AppDatabase
import com.android.akesosdkapp.model.local.DatabaseConstants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    // Dependency: AppDatabase
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, DatabaseConstants.mName).build()
    }

    // Dependency:
    single {
        val appDatabase: AppDatabase = get()
        appDatabase.userDao()
    }
}