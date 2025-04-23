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

@Entity(tableName = "barang")
data class BarangEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val namaBarang: String,
    val stok: Int,
    val image: String,
    val keterangan: String,
    val kondisi: String
)