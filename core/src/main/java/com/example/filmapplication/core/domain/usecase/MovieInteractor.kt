package com.example.filmapplication.core.domain.usecase

import com.example.filmapplication.core.domain.model.Movies
import com.example.filmapplication.core.domain.repository.IMoviesRepository

class MovieInteractor (private val moviesRepository: IMoviesRepository): MoviesUseCase {

    override fun getAllMovies() = moviesRepository.getAllMovies()

    override fun getDetailMovies(movieId: Int)= moviesRepository.getDetailMovies(movieId)

    override fun getFavMovies() = moviesRepository.getFavMovies()

    override fun setFavMovie(movie: Movies) {
        return moviesRepository.setFavMovie(movie)
    }
}