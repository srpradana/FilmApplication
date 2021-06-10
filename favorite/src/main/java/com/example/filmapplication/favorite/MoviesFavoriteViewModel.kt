package com.example.filmapplication.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.filmapplication.core.domain.usecase.MoviesUseCase

class MoviesFavoriteViewModel(moviesUseCase: MoviesUseCase): ViewModel() {

    val movies = moviesUseCase.getFavMovies().asLiveData()

}