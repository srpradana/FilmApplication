package com.example.filmapplication.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmapplication.core.databinding.ListMoviesBinding
import com.example.filmapplication.core.domain.model.Movies

class MoviesListAdapter : RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder>(){

    private val listItem = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null

    fun setFilm(listItem: List<Movies>?){
        if(listItem == null) return
        this.listItem.clear()
        this.listItem.addAll(listItem)
    }

    inner class MoviesViewHolder(private val binding: ListMoviesBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movies){
            with(binding){
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500"+movie.posterPath)
                    .into(imgPoster)
                posterName.text = movie.title
                posterDate.text = movie.date
                posterRating.text = movie.vote.toString()
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listItem[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemListBinding = ListMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size
}