package com.example.newsapp.data.datasource

import com.example.newsapp.data.api.WebServices
import com.example.newsapp.data.api.model.sourcesResponse.Sources
import com.example.newsapp.datasource.SourcesDataSource
import javax.inject.Inject

class SourcesOnlineDataSourceImpl @Inject constructor(private val webServices: WebServices) : SourcesDataSource {
    override suspend fun getSources(): List<Sources?>? {
       val response =  webServices.getSources()
        return response.sources
    }
}