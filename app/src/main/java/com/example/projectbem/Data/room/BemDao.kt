package com.example.projectbem.Data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: BemEntity)

    @Query("SELECT * FROM bem LIMIT 1")
    suspend fun getLoggedInUser(): BemEntity

    @Query("DELETE FROM bem")
    suspend fun clearUser()
}