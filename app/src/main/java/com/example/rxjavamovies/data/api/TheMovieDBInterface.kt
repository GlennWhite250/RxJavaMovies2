package com.example.moviesrxjavaexample.data.api

import com.example.moviesrxjavaexample.data.vo.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


//https://api.themoviedb.org/3/movie/621870?api_key=85d3276ca8094969e9b5c8c87a8ccce9&language=en-US


interface TheMovieDBInterface {

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}