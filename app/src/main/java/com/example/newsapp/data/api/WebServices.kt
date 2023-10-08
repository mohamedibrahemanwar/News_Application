package com.example.newsapp.data.api

import com.example.newsapp.data.api.model.newsResponse.NewsResponse
import com.example.newsapp.data.api.model.sourcesResponse.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    suspend fun getSources(@Query("apiKey") apiKey:String = Constant.apiKey): SourcesResponse

    @GET("v2/everything")
    suspend fun getNews(@Query("apiKey") apiKey: String = Constant.apiKey,
                        @Query("sources") sources :String,
    ) : NewsResponse

    @GET("v2/everything")
    suspend fun getNewsWithPagging(@Query("apiKey") apiKey: String = Constant.apiKey,
                        @Query("sources") sources :String,
                        @Query("pageSize") pageSize : Int,
                        @Query("page") page: Int
    ) : NewsResponse
}
