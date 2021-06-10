package com.example.filmapplication.core.domain.usecase

import com.example.filmapplication.core.domain.model.Movies
import com.example.filmapplication.core.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getAllMovies(): Flow<Resource<List<Movies>>>

    fun getDetailMovies(movieId: Int): Flow<Movies>

    fun getFavMovies(): Flow<List<Movies>>

    fun setFavMovie(movie: Movies)
}