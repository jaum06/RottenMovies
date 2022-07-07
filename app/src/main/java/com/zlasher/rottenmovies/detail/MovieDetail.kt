package com.zlasher.rottenmovies.detail

import java.io.Serializable

data class MovieDetail(
    val id: Int,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double
) : Serializable