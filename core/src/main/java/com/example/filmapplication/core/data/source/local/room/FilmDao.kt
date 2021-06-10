package com.example.filmapplication.core.data.source.local.room

import androidx.room.*
import com.example.filmapplication.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Query("SELECT * FROM movie_entities")
    fun getListMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_entities WHERE id = :moviesId")
    fun getMoviesById(moviesId: Int): Flow<MovieEntity>

    @Query("SELECT * FROM movie_entities where favCheck = 1")
    fun getFavListMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: List<MovieEntity>)

    @Update
    fun updateMovies(movieEntity: MovieEntity)
}