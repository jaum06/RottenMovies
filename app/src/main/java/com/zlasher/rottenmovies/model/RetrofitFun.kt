package com.zlasher.rottenmovies.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitFun {

    fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}