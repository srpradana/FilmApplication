package com.example.filmapplication.core.data.source.remote

import android.util.Log
import com.example.filmapplication.core.data.source.remote.network.ApiResponse
import com.example.filmapplication.core.data.source.remote.response.MoviesResult
import com.example.filmapplication.core.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService){

    suspend fun getAllMovies(): Flow<ApiResponse<List<MoviesResult>>> {
        return flow{
            try{
                val response = apiService.getMoviePopular()
                val dataArray = response.results
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}