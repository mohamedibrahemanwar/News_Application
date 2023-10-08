package com.example.newsapp.datasource

import com.example.newsapp.data.api.model.newsResponse.News

interface NewsDataSource {
    suspend fun getNews(sourcesId:String): List<News?>?
}