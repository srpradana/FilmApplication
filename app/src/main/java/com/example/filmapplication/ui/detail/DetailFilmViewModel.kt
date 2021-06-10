package com.example.filmapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.filmapplication.core.domain.model.Movies
import com.example.filmapplication.core.domain.usecase.MoviesUseCase

class DetailFilmViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {

    private lateinit var detailMovie: LiveData<Movies>

    fun getDetailMovie(id: String): LiveData<Movies>{
        detailMovie = moviesUseCase.getDetailMovies(id.toInt()).asLiveData()
        return detailMovie
    }

    fun setFavoriteMovie() {
        val movieResource = detailMovie.value
        movieResource?.let { moviesUseCase.setFavMovie(it) }
    }
}
