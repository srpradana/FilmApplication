package com.example.filmapplication.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.filmapplication.core.domain.usecase.MoviesUseCase

class MoviesViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {

    val movies = moviesUseCase.getAllMovies().asLiveData()
}