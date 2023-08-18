package com.rickyslash.kidcineproval.core.data.source.remote.network

import com.rickyslash.kidcineproval.core.data.source.remote.response.NowPlayingResponse
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(): NowPlayingResponse

}