package com.example.filmapplication.core.data.source.remote.network

import com.example.filmapplication.core.data.source.remote.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object{
        private const val TheMovieDB_KEY = "788b5ad16607ab6b84e77722aa816a69"
        private const val PAGE = 1
    }

    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Query("api_key") apiKey: String = TheMovieDB_KEY,
        @Query("page") page: Int = PAGE
    ): MoviesResponse
}