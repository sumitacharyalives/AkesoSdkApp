package com.android.akesosdkapp.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.akesosdkapp.model.local.dao.UserDao
import com.android.akesosdkapp.model.local.entity.User

@Database(entities = [User::class], version = DatabaseConstants.mVersion)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}