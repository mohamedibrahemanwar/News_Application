package com.example.newsapp.repository.sourcesrepository

import com.example.newsapp.data.api.model.sourcesResponse.Sources

interface SourcesRepositoryContract {
    suspend fun getSources() : List<Sources?>?
}