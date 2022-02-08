package com.example.newsapp.retrofit

import com.example.newsapp.retrofit.responce.Newsapi
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {
    @GET("top-headlines")
    suspend fun getAllnews(
        @Query("country") country:String,
        @Query("category") category:String,
        @Query("apiKey") apiKey: String,
        @Query("page") page:Int,
        @Query("pageSize")pageSize:Int,
        ):Newsapi
}