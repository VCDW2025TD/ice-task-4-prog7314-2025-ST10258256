package com.example.memestreamapp

import com.example.memestreamapp.data.Meme
import com.example.memestreamapp.data.MemeDao

class MemeRepository(
    private val memeDao: MemeDao,
    private val api: MemeApi
) {
    fun getMemes() = memeDao.getAllMemes() // always show cached memes

    suspend fun addMeme(meme: Meme) {
        memeDao.insertMeme(meme)
        if (isOnline()) {
            try {
                val response = api.uploadMeme(meme)
                if (response.isSuccessful) {
                    val serverMeme = response.body()
                    memeDao.updateMeme(meme.copy(isSynced = true, id = serverMeme?.id))
                }
            } catch (_: Exception) { /* keep isSynced=false */ }
        }
    }

    suspend fun syncUnsyncedMemes() {
        val unsynced = memeDao.getUnsyncedMemes()
        unsynced.forEach { meme ->
            try {
                val response = api.uploadMeme(meme)
                if (response.isSuccessful) {
                    val serverMeme = response.body()
                    memeDao.updateMeme(meme.copy(isSynced = true, id = serverMeme?.id))
                }
            } catch (_: Exception) { /* retry later */ }
        }
    }

    private fun isOnline(): Boolean {
        // Implement network connectivity check
        return true
    }
}

