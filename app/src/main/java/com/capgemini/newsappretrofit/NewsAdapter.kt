package com.capgemini.newsappretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val newsList: List<News>, val onSelection: (Int)-> Unit): RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    inner class NewsHolder(inflated: View): RecyclerView.ViewHolder(inflated){
        val img: ImageView = inflated.findViewById(R.id.imageView)
        val dateTV: TextView = inflated.findViewById(R.id.dateT)
        val titleTV: TextView = inflated.findViewById(R.id.titleT)
        val authorTV: TextView = inflated.findViewById(R.id.authorT)
        val descripTv: TextView = inflated.findViewById(R.id.descriptionT)

        init {
            itemView.setOnClickListener{
                onSelection(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        //inflate item xml and return viewholder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,
            parent, false)
        return NewsHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        //bind data to views
        val news = newsList[position]

        holder.authorTV.text = news.author
        holder.titleTV.text = news.title
        holder.dateTV.text = news.publishedAt
        holder.descripTv.text = news.description

        news.urlToImage?.let {
            Glide.with(holder.itemView).load(it).into(holder.img)
        }

    }

}