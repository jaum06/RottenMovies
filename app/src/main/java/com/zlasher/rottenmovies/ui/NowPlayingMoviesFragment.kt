package com.zlasher.rottenmovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zlasher.rottenmovies.adapter.MovieAdapter
import com.zlasher.rottenmovies.databinding.FragmentNowPlayingMoviesBinding
import com.zlasher.rottenmovies.detail.MovieDetail
import com.zlasher.rottenmovies.model.APIService
import com.zlasher.rottenmovies.model.RetrofitFun
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NowPlayingMoviesFragment : Fragment(), RetrofitFun {

    private lateinit var nowPlayingMoviesBinding: FragmentNowPlayingMoviesBinding
    private lateinit var adapter: MovieAdapter
    private val moviesList = mutableListOf<MovieDetail>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        nowPlayingMoviesBinding =
            FragmentNowPlayingMoviesBinding.inflate(inflater, container, false)
        return nowPlayingMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        searchMovies()
    }

    private fun initRecyclerView() {
        adapter = MovieAdapter(moviesList, onItemClicked = { onMovieClicked(it) })
        nowPlayingMoviesBinding.nowPlayingRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        nowPlayingMoviesBinding.nowPlayingRecycler.adapter = adapter
    }

    private fun onMovieClicked(movie: MovieDetail) {
        findNavController().navigate(
            ContainerMoviesFragmentDirections.actionContainerMoviesFragmentToDetailMovieFragment(
                movie
            )
        )
    }

    private fun searchMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java)
                .getMovies("now_playing?api_key=8bde5af3f92f758581274c47ab874d13&language=es-CO&page=1")
            val movie = call.body()

            activity?.runOnUiThread {
                if (call.isSuccessful) {
                    val movieList: List<MovieDetail> = (movie?.movieList ?: emptyList())
                    moviesList.clear()
                    moviesList.addAll(movieList)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
    }
}