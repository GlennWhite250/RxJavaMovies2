package com.example.moviesrxjavaexample.single_movie_details

import androidx.lifecycle.LiveData
import com.example.moviesrxjavaexample.data.api.TheMovieDBInterface
import com.example.moviesrxjavaexample.data.repository.MovieDetailsNetworkSource
import com.example.moviesrxjavaexample.data.repository.NetworkState
import com.example.moviesrxjavaexample.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService: TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkSource

    fun fetchSingleMovieDetails (comppositeDisposable: CompositeDisposable, movieId: Int): LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailsNetworkSource(apiService, comppositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieDetailsResponse
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}