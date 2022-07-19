package com.zlasher.rottenmovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.zlasher.rottenmovies.databinding.FragmentDetailMovieBinding
import com.zlasher.rottenmovies.model.APIService
import com.zlasher.rottenmovies.model.RetrofitFun
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMovieFragment : Fragment(), RetrofitFun {

    private lateinit var detailMovieBinding: FragmentDetailMovieBinding
    private val args: DetailMovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailMovieBinding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return detailMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchMovie()
    }

    private fun searchMovie() {
        val movie = args.movie
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java)
                .getMovieDetail("${movie.id}?api_key=8bde5af3f92f758581274c47ab874d13&language=es-CO")
            val movieDetail = call.body()

            activity?.runOnUiThread {
                if (call.isSuccessful) {
                    val urlPosterImage =
                        "https://image.tmdb.org/t/p/original${movieDetail?.poster_path}"

                    with(detailMovieBinding) {
                        Glide.with(posterImage)//donde cargarla xml
                            .load(urlPosterImage)//valor a cargar
                            .into(posterImage)
                        titleText.text = movieDetail?.title
                        releaseDateText.text = movieDetail?.release_date
                        voteAverageText.text = "${movieDetail?.vote_average}"
                        taglineText.text = movieDetail?.tagline
                        overview.text = movieDetail?.overview
                        originalTitle.text = movieDetail?.original_title
                        budget.text = "${movieDetail?.budget}"
                        revenue.text = "${movieDetail?.revenue}"
                    }

                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(activity, "Error 2", Toast.LENGTH_SHORT).show()
    }

}