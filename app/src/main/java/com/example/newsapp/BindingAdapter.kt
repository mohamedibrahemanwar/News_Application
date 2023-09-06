package com.example.newsapp

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.newsapp.R
@BindingAdapter("url")
fun bindImageWithUrl(imageView : ImageView, url:String){
    Glide.with(imageView)
        .load(url)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(imageView)
}
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView,url: String){
    Glide.with(imageView)
        .load(url)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(imageView)
}
@BindingAdapter("action")
fun clickLanucher(view: View,url: String?){
    view.setOnClickListener{
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        view.context.startActivity(intent)
    }
}