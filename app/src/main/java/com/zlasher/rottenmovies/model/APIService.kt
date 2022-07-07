package com.zlasher.rottenmovies.model

import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("popular?api_key=8bde5af3f92f758581274c47ab874d13&language=es-CO&page=1#")
    suspend fun getMovies(): Response<MoviesResponse>
}