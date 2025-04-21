package com.example.projectbem.Data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bem")
data class BemEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
    val role: String,
    val token: String
)
