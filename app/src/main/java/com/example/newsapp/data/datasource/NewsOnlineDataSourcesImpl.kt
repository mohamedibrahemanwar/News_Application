package com.example.newsapp.data.datasource

import com.example.newsapp.data.api.WebServices
import com.example.newsapp.data.api.model.newsResponse.News
import com.example.newsapp.datasource.NewsDataSource
import javax.inject.Inject

class NewsOnlineDataSourcesImpl @Inject constructor(val webServices: WebServices) : NewsDataSource {
    override suspend fun getNews(sourcesId: String): List<News?>? {
        val response = webServices.getNews(sources = sourcesId)
        return response.articles
    }
}