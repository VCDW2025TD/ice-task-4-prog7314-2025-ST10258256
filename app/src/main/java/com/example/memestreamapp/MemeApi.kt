package com.example.memestreamapp

import com.example.memestreamapp.data.Meme
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


interface MemeApi {
    @GET("memes")
    suspend fun getAllMemes(@Query("userId") userId: String? = null): List<Meme>

    @POST("memes")
    suspend fun uploadMeme(@Body meme: Meme): Response<Meme>

    @GET("/memes")
    suspend fun getMemes(): List<Meme>

    @Multipart
    @POST("memes")
    suspend fun uploadMeme(
        @Part image: MultipartBody.Part,
        @Part("userId") userId: RequestBody,
        @Part("caption") caption: RequestBody
    ): Response<MemeResponse>


    data class MemeResponse(
        val id: String,
        val imageUrl: String,
        val caption: String
    )
}
