package com.example.newsapp.retrofit.responce

data class Newsapi(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)