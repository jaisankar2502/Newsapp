package com.example.newsapp

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.newsapp.retrofit.responce.Article

class NewsViewmodel @ViewModelInject constructor(
    private  val newsRepository: NewsRepository
):ViewModel() {
    val  list:LiveData<PagingData<Article>> =newsRepository.getAllnewsStream()
}