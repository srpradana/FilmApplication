package com.example.filmapplication.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmapplication.core.databinding.ListFavoriteBinding
import com.example.filmapplication.core.domain.model.Movies

class MoviesFavoriteAdapter : RecyclerView.Adapter<MoviesFavoriteAdapter.FavMoviesViewHolder>(){

    private val listItem = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null

    fun setFilm(listItem: List<Movies>?){
        if(listItem == null) return
        this.listItem.clear()
        this.listItem.addAll(listItem)
    }

    inner class FavMoviesViewHolder(private val binding: ListFavoriteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movies){
            with(binding){
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500"+movie.posterPath)
                    .into(imgPoster)
                posterName.text = movie.title
                posterDate.text = movie.date
                posterRating.text = movie.vote.toString()
                posterOverview.text = movie.overview
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listItem[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMoviesViewHolder {
        val itemListBinding = ListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavMoviesViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: FavMoviesViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size
}