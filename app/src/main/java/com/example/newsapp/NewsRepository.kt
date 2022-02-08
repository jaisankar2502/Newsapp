package com.example.newsapp

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.newsapp.paging.NewsPagingSource
import com.example.newsapp.retrofit.NewsInterface
import com.example.newsapp.retrofit.responce.Article

class NewsRepository(val newsInterface: NewsInterface) {
    fun getAllnewsStream():LiveData<PagingData<Article>> = Pager(
        config = PagingConfig(
            10,
            5,
            false
        ),
        pagingSourceFactory = {
            NewsPagingSource(newsInterface)
        }
    ).liveData
}