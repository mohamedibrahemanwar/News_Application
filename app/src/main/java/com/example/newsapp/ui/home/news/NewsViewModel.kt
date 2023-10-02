package com.example.newsapp.ui.home.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.ViewError
import com.example.newsapp.api.ApiManger
import com.example.newsapp.api.model.newsResponse.News
import com.example.newsapp.api.model.newsResponse.NewsResponse
import com.example.newsapp.api.model.sourcesResponse.Sources
import com.example.newsapp.api.model.sourcesResponse.SourcesResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class NewsViewModel : ViewModel() {
    val shouldShowLoading = MutableLiveData<Boolean>()
    val soucrcesLiveData = MutableLiveData<List<Sources?>?>()
    val newsLiveData = MutableLiveData<List<News?>?>()
    val viewError = MutableLiveData<ViewError>()
    fun getNewsSources() {
        viewModelScope.launch {
            shouldShowLoading.postValue(true)
            try {
                val newsSources = ApiManger.getApis().getSources()
                soucrcesLiveData.postValue(newsSources.sources)
            } catch (e: HttpException) {
                val errorBodyJsonString = e.response()?.errorBody()?.string()
                val response = Gson().fromJson(errorBodyJsonString, SourcesResponse::class.java)

                viewError.postValue(ViewError(message = response.message) {
                    getNewsSources()
                })
            } catch (e: Exception) {
                viewError.postValue(ViewError(
                    t = e
                ) {
                    getNewsSources()
                })
            } finally {
                shouldShowLoading.postValue(false)

            }

        }

    }

    fun getNews(sourcesId: String?, pageSize: Int, page: Int) {
        viewModelScope.launch {
            try {
                shouldShowLoading.postValue(true)

                val news = ApiManger.getApis()
                    .getNews(sources = sourcesId ?: "", pageSize = pageSize, page = page)
                newsLiveData.postValue(news.articles)

            } catch (e: HttpException) {
                val responseJsonError = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(responseJsonError, NewsResponse::class.java)
                viewError.postValue(
                    ViewError(message = errorResponse.message) {
                        getNews(sourcesId, pageSize, page)
                    }
                )
            } catch (e: Exception) {
                viewError.postValue(
                    ViewError(t = e) {
                        getNews(sourcesId, pageSize, page)
                    }
                )
            } finally {
                shouldShowLoading.postValue(false)

            }
        }


    }
}


