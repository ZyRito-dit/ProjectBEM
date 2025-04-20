package com.example.projectbem.Data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BemEntity::class], version = 1, exportSchema = false)
abstract class BemDatabase: RoomDatabase() {
    abstract fun bemDao(): BemDao

    companion object{
        @Volatile
        private var INSTANCE: BemDatabase? = null

        fun getInstance(context: Context): BemDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    BemDatabase::class.java,
                    "bem_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}