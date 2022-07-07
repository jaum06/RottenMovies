package com.zlasher.rottenmovies.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zlasher.rottenmovies.R.layout.popular_movies_view
import com.zlasher.rottenmovies.databinding.PopularMoviesViewBinding
import com.zlasher.rottenmovies.detail.MovieDetail

class MovieAdapter(private val movies: List<MovieDetail>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val moviesBinding = PopularMoviesViewBinding.bind(view)

        fun bind(movie: MovieDetail) {
            with(moviesBinding) {
                val urlPosterImage = "https://image.tmdb.org/t/p/original${movie.poster_path}"
                Glide.with(posterImage)//donde cargarla xml
                    .load(urlPosterImage)//valor a cargar
                    .into(posterImage)
                titleMovieText.text = movie.title
                releaseDateText.text = movie.release_date
                Log.d("prueba", "${movie.vote_average}")
                voteAverageText.text = "${movie.vote_average}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(popular_movies_view, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size
}