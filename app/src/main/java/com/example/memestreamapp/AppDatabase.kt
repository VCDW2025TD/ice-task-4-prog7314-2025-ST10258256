package com.example.memestreamapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Meme::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memeDao(): MemeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "memestream_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


