package com.example.memestreamapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memes")
data class Meme(
    @PrimaryKey(autoGenerate = true)
    val localId: Long = 0L,  // Must be Long for autoGenerate

    val id: String? = null,        // MongoDB _id
    val userId: String,
    val imageUrl: String,
    val caption: String? = null,
    val lat: Double? = null,
    val lng: Double? = null,
    val timestamp: Long,           // epoch millis
    val isSynced: Boolean = false
)
