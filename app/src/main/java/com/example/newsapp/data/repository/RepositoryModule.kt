package com.example.newsapp.data.repository

import com.example.newsapp.data.datasource.NewsOnlineDataSourcesImpl
import com.example.newsapp.data.datasource.SourcesOnlineDataSourceImpl
import com.example.newsapp.datasource.NewsDataSource
import com.example.newsapp.datasource.SourcesDataSource
import com.example.newsapp.repository.newssrepository.NewsRepositoryContract
import com.example.newsapp.repository.sourcesrepository.SourcesRepositoryContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideSourcesRepository(
        sourcesRepo:SourcesRepositoryImpl
    ) : SourcesRepositoryContract

    @Binds
    abstract fun provideSourcesDataSource(
        dataSource: SourcesOnlineDataSourceImpl
    ): SourcesDataSource

    @Binds
    abstract fun provideNewsRepository(
        newsRepo: NewsRepositoryImpl
    ): NewsRepositoryContract
    @Binds
    abstract fun provideNewsDataSource(
        newsDataSource: NewsOnlineDataSourcesImpl
    ): NewsDataSource
}