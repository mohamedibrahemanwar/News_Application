package com.example.newsapp.ui.home.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.ViewError
import com.example.newsapp.data.api.model.newsResponse.News
import com.example.newsapp.data.api.model.newsResponse.NewsResponse
import com.example.newsapp.data.api.model.sourcesResponse.Sources
import com.example.newsapp.data.api.model.sourcesResponse.SourcesResponse
import com.example.newsapp.repository.newssrepository.NewsRepositoryContract
import com.example.newsapp.repository.sourcesrepository.SourcesRepositoryContract
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val sourcesRepo : SourcesRepositoryContract,
    private val newsRepo: NewsRepositoryContract
) : ViewModel() {
    val shouldShowLoading = MutableLiveData<Boolean>()
    val soucrcesLiveData = MutableLiveData<List<Sources?>?>()
    val newsLiveData = MutableLiveData<List<News?>?>()
    val viewError = MutableLiveData<ViewError>()

    fun getNewsSources() {
        viewModelScope.launch {
            shouldShowLoading.postValue(true)
            try {
                val newsSources = sourcesRepo.getSources()
                soucrcesLiveData.postValue(newsSources)
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

                val news = newsRepo.getNews(sourcesId ?: "")
                newsLiveData.postValue(news)

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


