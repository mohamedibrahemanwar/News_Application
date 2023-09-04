package com.example.newsapp.ui.home.news

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.ApiManger
import com.example.newsapp.api.model.newsResponse.News
import com.example.newsapp.api.model.newsResponse.NewsResponse
import com.example.newsapp.api.model.sourcesResponse.Sources
import com.example.newsapp.api.model.sourcesResponse.SourcesResponse
import com.example.newsapp.ui.ViewError
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    val shouldShowLoading = MutableLiveData<Boolean>()
    val soucrcesLiveData = MutableLiveData<List<Sources?>?>()
    val newsLiveData = MutableLiveData<List<News?>?>()
    val viewError = MutableLiveData<ViewError>()
    fun getNewsSources() {
        shouldShowLoading.postValue(true)
        ApiManger
            .getApis()
            .getSoucrces()
            .enqueue(object : Callback<SourcesResponse> {
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    shouldShowLoading.postValue(false)
                    viewError.postValue(ViewError(
                        t = t
                    ) {
                        getNewsSources()
                    })
                }

                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    shouldShowLoading.postValue(false)
                    if (response.isSuccessful) {
                        //show tabs in fragment
                        soucrcesLiveData.postValue(response.body()?.sources)
                    } else {
                        val errorBodyJsonString = response.errorBody()?.string()
                        val response =
                            Gson().fromJson(errorBodyJsonString, SourcesResponse::class.java)

                        viewError.postValue(ViewError(message = response.message){
                            getNewsSources()
                        })
                    }
                }
            })
    }

    fun getNews(sourcesId: String?) {
        shouldShowLoading.postValue(true)
        ApiManger.getApis()
            .getNews(sources = sourcesId ?: "")
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {

                    shouldShowLoading.postValue(false)
                    if (response.isSuccessful) {
                        // show all news
                        newsLiveData.postValue(response.body()?.articles)
                        return
                    }
                    val responseJsonError = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(responseJsonError, NewsResponse::class.java)

//                    handleError(message = errorResponse.message){
//                        getNews(sourcesId)
//                    }
                    viewError.postValue(
                        ViewError(message = errorResponse.message) {
                            getNews(sourcesId)
                        }
                    )

                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    shouldShowLoading.postValue(false)
//                    handleError(t) {
//                        getNews(sourcesId)
//                    }
                    viewError.postValue(
                        ViewError(t = t) {
                            getNews(sourcesId)
                        }
                    )
                }

            })
    }


}