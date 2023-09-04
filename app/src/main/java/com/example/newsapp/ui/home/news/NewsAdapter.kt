package com.example.newsapp.ui.home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.api.model.newsResponse.News
import com.example.newsapp.databinding.ItemNewsBinding

class NewsAdapter(var newsList: List<News?>? = null) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(val itemBinding: ItemNewsBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(news: News?) {
            itemBinding.news = news
            // itemBinding.executePendingBindings()
            itemBinding.invalidateAll()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList!![position]
//        holder.itemBinding.title.text = news?.title
//        holder.itemBinding.description.text = news?.description
        holder.bind(news)
        holder.itemBinding.image.setOnClickListener {
            onItemClickListner?.onClick(position, news!!)
        }
    }

    fun bindNews(articles: List<News?>?) {
        newsList = articles
        notifyDataSetChanged()
    }

    var onItemClickListner: OnItemClickListner? = null

    interface OnItemClickListner {
        fun onClick(position: Int, news: News)
    }
}