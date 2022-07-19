package com.zlasher.rottenmovies.detail

import java.io.Serializable

data class GetMovieDetail(
    val budget: Int,
    val genres: List<Genre>,
    val id: Int,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val tagline: String,
    val title: String,
    val vote_average: Float
) : Serializable