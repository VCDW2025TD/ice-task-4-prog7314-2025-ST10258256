//package com.example.memestreamapp.data
//
//import com.example.memestreamapp.MemeApi
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object RetrofitInstance {
//
//    private val retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl("https://memestream-z8fk.onrender.com") // Replace with your Render backend URL
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    val memeApi: MemeApi by lazy {
//        retrofit.create(MemeApi::class.java)
//    }
//}

package com.example.memestreamapp.network

import com.example.memestreamapp.MemeApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://memestream-z8fk.onrender.com") // Replace with your Render API base URL
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val memeApi: MemeApi by lazy {
        retrofit.create(MemeApi::class.java)
    }
}

