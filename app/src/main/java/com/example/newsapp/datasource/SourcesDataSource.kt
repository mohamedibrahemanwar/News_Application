package com.example.newsapp.datasource

import com.example.newsapp.data.api.model.sourcesResponse.Sources

interface SourcesDataSource {
    suspend fun getSources(): List<Sources?>?
}