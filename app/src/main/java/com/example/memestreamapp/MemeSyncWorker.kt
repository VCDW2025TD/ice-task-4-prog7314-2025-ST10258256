package com.example.memestreamapp

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.memestreamapp.data.AppDatabase
import com.example.memestreamapp.network.RetrofitInstance

class MemeSyncWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val db = AppDatabase.getInstance(context)
    private val memeDao = db.memeDao()
    private val api = RetrofitInstance.memeApi // Your Retrofit setup
    private val repository = MemeRepository(memeDao, api)

    override suspend fun doWork(): Result {
        return try {
            repository.syncUnsyncedMemes()
            Result.success()
        } catch (e: Exception) {
            Result.retry() // Retry later if network fails
        }
    }
}

