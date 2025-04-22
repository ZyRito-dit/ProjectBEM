package com.example.projectbem.Data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bem")
data class BemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,
    val token: String,
    val role: String,
    val username: String?,
    val nim: String?,
    val image: String?,
    val divisi: String?
)