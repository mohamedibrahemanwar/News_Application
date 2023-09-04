package com.example.newsapp.ui

import com.example.newsapp.ui.home.news.NewsFragment

data class ViewError(
    val message :String? = null,
    val t : Throwable? = null,
    val onTryAgainClickListener: OnTryAgainClickListener?= null
)
fun interface OnTryAgainClickListener{
    fun onTryAgainClicked()
}

