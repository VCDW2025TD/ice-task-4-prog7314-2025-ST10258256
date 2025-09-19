package com.example.memestreamapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MemeDao {
    @Query("SELECT * FROM memes ORDER BY timestamp DESC")
    fun getAllMemes(): List<Meme>

    @Query("SELECT * FROM memes WHERE isSynced = 0")
    suspend fun getUnsyncedMemes(): List<Meme>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeme(meme: Meme)

    @Update
    suspend fun updateMeme(meme: Meme)
}

