package com.example.newsapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManger {
    //Builder
    private var retrofit : Retrofit?=null

    private fun getInstance() : Retrofit{
        if (retrofit==null){
            retrofit = Retrofit.Builder().baseUrl(Constant.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return  retrofit!!
    }

    fun getApis(): WebServices{
        return  getInstance().create(WebServices::class.java)
    }



}