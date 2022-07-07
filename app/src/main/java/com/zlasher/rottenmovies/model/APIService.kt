package com.zlasher.rottenmovies.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getMovies(@Url url: String): Response<MoviesResponse>
}