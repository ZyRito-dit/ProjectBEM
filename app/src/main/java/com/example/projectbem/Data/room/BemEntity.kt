package com.example.projectbem.Data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bem")
data class BemEntity (
    @PrimaryKey
    val id: Int,
    val username: String,
    val image: String,
    val statusOnline: Boolean,
    val nim: String,
    val role: String,
    val createdAt: String,
    val updatedAt: String,
    val divisi: String
)