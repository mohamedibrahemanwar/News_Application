package com.example.newsapp.data.repository

import com.example.newsapp.data.api.model.sourcesResponse.Sources
import com.example.newsapp.datasource.SourcesDataSource
import com.example.newsapp.repository.sourcesrepository.SourcesRepositoryContract
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(private val sourcesOnlineDataSource: SourcesDataSource) : SourcesRepositoryContract {
    override suspend fun getSources(): List<Sources?>? {
      val sources=  sourcesOnlineDataSource.getSources()
        return sources
    }
}