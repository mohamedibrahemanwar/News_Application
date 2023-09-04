package com.example.newsapp.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.api.Constant
import com.example.newsapp.api.model.newsResponse.News
import com.example.newsapp.databinding.ActivityFullNewsBinding

class FullNewsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityFullNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFullNewsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initParms()
        bindData()
    }

    private fun bindData() {
        Glide.with(applicationContext)
            .load(newNews?.urlToImage)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(viewBinding.image)

        viewBinding.title.text = newNews?.title
        viewBinding.content.text = newNews?.content
        viewBinding.description.text = newNews?.description
        viewBinding.author.text = newNews?.author
    }
    var newNews: News? = null
    private fun initParms() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            newNews = intent.getParcelableExtra(Constant.OBJ_KEY, News::class.java)!!
        } else {
            newNews = intent.getParcelableExtra("OBJ_KEY") as News?

        }
    }
}