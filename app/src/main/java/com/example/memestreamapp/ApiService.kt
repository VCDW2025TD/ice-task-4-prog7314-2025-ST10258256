package com.example.memestreamapp

import com.example.memestreamapp.data.Meme
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("memes")
    suspend fun getMemes(): List<Meme>

    @GET("memes")
    suspend fun getUserMemes(@Query("userId") userId: String): List<Meme>

    @POST("memes")
    suspend fun postMeme(@Body memePost: MemePost)
}
