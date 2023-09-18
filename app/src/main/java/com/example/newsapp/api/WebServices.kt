package com.example.newsapp.api

import com.example.newsapp.api.model.newsResponse.NewsResponse
import com.example.newsapp.api.model.sourcesResponse.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey") apiKey:String = Constant.apiKey ): Call<SourcesResponse>

    @GET("v2/everything")
    fun getNews(@Query("apiKey") apiKey: String = Constant.apiKey,
                @Query("sources") sources :String,
                @Query("pageSize") pageSize : Int,
                @Query("page") page: Int
    ) : Call<NewsResponse>
}
