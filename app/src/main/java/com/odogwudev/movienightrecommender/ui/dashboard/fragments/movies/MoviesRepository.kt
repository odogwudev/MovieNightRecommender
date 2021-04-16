package com.odogwudev.movienightrecommender.ui.dashboard.fragments.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.odogwudev.movienightrecommender.models.MovieGenres
import com.odogwudev.movienightrecommender.models.MovieGenresFeed
import com.odogwudev.movienightrecommender.models.Movies
import com.odogwudev.movienightrecommender.models.UpcomingMovies
import com.odogwudev.movienightrecommender.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository {

    val upcomingMovies = MutableLiveData<List<Movies>>()
    val trendingMovies = MutableLiveData<List<Movies>>()
    val movieGenres = MutableLiveData<List<MovieGenres>>()
    val topRatedMovies = MutableLiveData<List<Movies>>()

    fun getUpcomingMovies() {
        ApiClient.getCountries().enqueue(object : Callback<UpcomingMovies> {
            override fun onResponse(
                call: Call<UpcomingMovies>,
                response: Response<UpcomingMovies>
            ) {
                Log.d("", response.body().toString())
                upcomingMovies.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<UpcomingMovies>, t: Throwable) {
                upcomingMovies.postValue(null)
            }
        })
    }

    fun getTrendingMovies() {
        ApiClient.getTrendingMovies().enqueue(object : Callback<UpcomingMovies> {
            override fun onResponse(
                call: Call<UpcomingMovies>,
                response: Response<UpcomingMovies>
            ) {
                Log.d("", response.body().toString())
                trendingMovies.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<UpcomingMovies>, t: Throwable) {
                trendingMovies.postValue(null)
            }
        })
    }

    fun getMovieGenres() {
        ApiClient.getMovieGenres().enqueue(object : Callback<MovieGenresFeed> {
            override fun onResponse(
                call: Call<MovieGenresFeed>,
                response: Response<MovieGenresFeed>
            ) {
                Log.d("", response.body().toString())
                movieGenres.postValue(response.body()?.genres)
            }

            override fun onFailure(call: Call<MovieGenresFeed>, t: Throwable) {
                movieGenres.postValue(null)
            }
        })
    }

    fun getTopRatedMovies() {
        ApiClient.getTopRatedMovies().enqueue(object : Callback<UpcomingMovies> {
            override fun onResponse(
                call: Call<UpcomingMovies>,
                response: Response<UpcomingMovies>
            ) {
                Log.d("", response.body().toString())
                topRatedMovies.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<UpcomingMovies>, t: Throwable) {
                topRatedMovies.postValue(null)
            }
        })
    }
}