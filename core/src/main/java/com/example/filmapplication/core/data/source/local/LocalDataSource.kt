package com.example.filmapplication.core.data.source.local

import com.example.filmapplication.core.data.source.local.room.FilmDao
import com.example.filmapplication.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val mFilmDao: FilmDao) {

    fun getListMovies(): Flow<List<MovieEntity>> = mFilmDao.getListMovies()

    fun getMoviesById(moviesId: Int): Flow<MovieEntity> = mFilmDao.getMoviesById(moviesId)

    fun getFavMovie(): Flow<List<MovieEntity>> = mFilmDao.getFavListMovie()

    suspend fun insertMovie(movie : List<MovieEntity>) =  mFilmDao.insertMovie(movie)

    fun setFavMovie(movie: MovieEntity){
        movie.favCheck= !movie.favCheck
        mFilmDao.updateMovies(movie)
    }
}