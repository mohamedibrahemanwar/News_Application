package com.example.newsapp.data.repository

import com.example.newsapp.data.api.model.newsResponse.News
import com.example.newsapp.datasource.NewsDataSource
import com.example.newsapp.repository.newssrepository.NewsRepositoryContract
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val dataSources : NewsDataSource) : NewsRepositoryContract {
    override suspend fun getNews(sourceId: String): List<News?>? {
        return dataSources.getNews(sourceId)
    }
}