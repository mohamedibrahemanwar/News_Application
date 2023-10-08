package com.example.newsapp.ui.home.fullnews

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.api.Constant
import com.example.newsapp.data.api.model.newsResponse.News
import com.example.newsapp.databinding.ActivityFullNewsBinding

class FullNewsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityFullNewsBinding
    private lateinit var news: News
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFullNewsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        news = ((intent.getParcelableExtra("OBJ_KEY") as News?)!!)
        viewBinding.newsData = news
    }


}