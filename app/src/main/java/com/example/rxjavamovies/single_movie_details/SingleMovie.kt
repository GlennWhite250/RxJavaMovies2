package com.example.moviesrxjavaexample.single_movie_details

import android.graphics.Movie
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.moviesrxjavaexample.*
import com.example.moviesrxjavaexample.data.api.TheMovieDBClient
import com.example.moviesrxjavaexample.data.api.TheMovieDBInterface
import com.example.moviesrxjavaexample.data.vo.MovieDetails
import com.example.rxjavamovies.R
import kotlinx.android.synthetic.main.activity_single_movie.*

class SingleMovie: AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        val movieID = 1

        val apiService: TheMovieDBInterface = TheMovieDBClient.getClient()
        movieRepository = MovieDetailsRepository(apiService)

        viewModel = getViewModel(movieID)
        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })
    }

    fun bindUI(it: MovieDetails){
        movie_title_tv.text = it.title
        movie_tagline_tv.text = it.tagline
        movie_release_date_tv.text = it.releaseDate

    }

    private fun getViewModel(movieID: Int): SingleMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SingleMovieViewModel(movieRepository, movieID) as T
            }
        }) [SingleMovieViewModel::class.java]
    }
}