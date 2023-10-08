package com.example.newsapp.repository.newssrepository

import com.example.newsapp.data.api.model.newsResponse.News

interface NewsRepositoryContract {
    suspend fun getNews(sourceId:String): List<News?>?
}