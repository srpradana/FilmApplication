package com.example.filmapplication.core.data.source

import com.example.filmapplication.core.data.source.local.LocalDataSource
import com.example.filmapplication.core.data.source.remote.network.ApiResponse
import com.example.filmapplication.core.data.source.remote.RemoteDataSource
import com.example.filmapplication.core.data.source.remote.response.MoviesResult
import com.example.filmapplication.core.domain.model.Movies
import com.example.filmapplication.core.domain.repository.IMoviesRepository
import com.example.filmapplication.core.utils.AppExecutors
import com.example.filmapplication.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepository(private val remoteDataSource: RemoteDataSource,
                       private val localDataSource: LocalDataSource,
                       private val appExecutors: AppExecutors
)
: IMoviesRepository {

    override fun getAllMovies(): Flow<Resource<List<Movies>>> {
        return object : com.example.filmapplication.core.data.source.NetworkBoundResource<List<Movies>, List<MoviesResult>>(){
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.getListMovies().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesResult>>> {
                return remoteDataSource.getAllMovies()
            }

            override suspend fun saveCallResult(data: List<MoviesResult>) {
                val moviesList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(moviesList)
            }
        }.asFlow()
    }

    override fun getDetailMovies(movieId: Int): Flow<Movies>{
        return localDataSource.getMoviesById(movieId).map { DataMapper.mapEntityToDomainSingle(it) }
    }

    override fun getFavMovies(): Flow<List<Movies>> {
        return localDataSource.getFavMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavMovie(movie: Movies) {
        val moviesEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute{
            localDataSource.setFavMovie(moviesEntity)
        }
    }
}