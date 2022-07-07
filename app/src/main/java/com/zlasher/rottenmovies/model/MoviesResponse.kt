package com.zlasher.rottenmovies.model

import com.google.gson.annotations.SerializedName
import com.zlasher.rottenmovies.detail.MovieDetail

data class MoviesResponse(
    @SerializedName("results") var movieList: List<MovieDetail>
)