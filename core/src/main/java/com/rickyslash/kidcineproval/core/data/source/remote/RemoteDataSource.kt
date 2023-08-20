package com.rickyslash.kidcineproval.core.data.source.remote

import com.rickyslash.kidcineproval.core.data.source.remote.network.ApiResponse
import com.rickyslash.kidcineproval.core.data.source.remote.network.ApiService
import com.rickyslash.kidcineproval.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getNowPlaying()
                val result = response.results
                if (result.isNotEmpty()) {
                    emit(ApiResponse.Success(result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}