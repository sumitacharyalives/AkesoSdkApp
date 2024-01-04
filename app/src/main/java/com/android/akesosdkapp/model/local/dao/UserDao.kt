package com.android.akesosdkapp.model.local.dao

import androidx.room.*
import com.android.akesosdkapp.model.local.DatabaseConstants
import com.android.akesosdkapp.model.local.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM " + DatabaseConstants.mTableUser)
    suspend fun getAll(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userList: List<User>)

    @Query("DELETE FROM " + DatabaseConstants.mTableUser)
    suspend fun deleteAll()

    @Transaction
    suspend fun insertAllUsers(userList: List<User>) {
        insert(userList)
    }
}