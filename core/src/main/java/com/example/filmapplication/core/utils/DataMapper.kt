package com.example.filmapplication.core.utils

import com.example.filmapplication.core.data.source.local.entity.MovieEntity
import com.example.filmapplication.core.data.source.remote.response.MoviesResult
import com.example.filmapplication.core.domain.model.Movies

object DataMapper {
    fun mapResponsesToEntities(input: List<MoviesResult>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movies = MovieEntity(
                id = it.id,
                posterPath = it.posterPath,
                title = it.originalTitle,
                date = it.releaseDate,
                overview = it.overview,
                vote = it.voteAverage,
                favCheck = false
            )
            movieList.add(movies)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movies> =
        input.map {
            Movies(
                id = it.id,
                posterPath = it.posterPath,
                title = it.title,
                date = it.date,
                overview = it.overview,
                vote = it.vote,
                favCheck = false
            )
        }

    fun mapDomainToEntity(input: Movies) =
        MovieEntity(
            id = input.id,
            posterPath = input.posterPath,
            title = input.title,
            date = input.date,
            overview = input.overview,
            vote = input.vote,
            favCheck = input.favCheck
        )

    fun mapEntityToDomainSingle(input: MovieEntity) = Movies(
        id = input.id,
        posterPath = input.posterPath,
        title = input.title,
        date = input.date,
        overview = input.overview,
        vote = input.vote,
        favCheck = input.favCheck
    )
}