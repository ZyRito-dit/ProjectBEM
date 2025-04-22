package com.example.projectbem.Data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: BemEntity)

    @Query("SELECT * FROM bem WHERE id = 1")
    suspend fun getUser(): BemEntity?

    @Query("DELETE FROM bem")
    suspend fun deleteUser()

}