package com.odogwudev.movienightrecommender.ui.dashboard.fragments.tvSeries


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.odogwudev.movienightrecommender.models.MovieGenres
import com.odogwudev.movienightrecommender.models.MovieGenresFeed
import com.odogwudev.movienightrecommender.models.TvSeries
import com.odogwudev.movienightrecommender.models.TvSeriesFeed
import com.odogwudev.movienightrecommender.network.ApiClient
import retrofit2.Callback

class TvSeriesRepository {

    val featuredTvSeries = MutableLiveData<List<TvSeries>>()
    val airingToday = MutableLiveData<List<TvSeries>>()
    val topRated = MutableLiveData<List<TvSeries>>()
    val popularTvSeries = MutableLiveData<List<TvSeries>>()
    val tvSeriesGenres = MutableLiveData<List<MovieGenres>>()

    fun getFeaturedTvSeries() {
        ApiClient.getFeaturedTvSeries().enqueue(object : Callback<TvSeriesFeed> {
            override fun onResponse(
                call: retrofit2.Call<TvSeriesFeed>,
                response: retrofit2.Response<TvSeriesFeed>
            ) {
                Log.d("", response.body().toString())
                featuredTvSeries.postValue(response.body()?.results)
            }

            override fun onFailure(call: retrofit2.Call<TvSeriesFeed>, t: Throwable) {
                featuredTvSeries.postValue(null)
            }
        })
    }

    fun getAiringTodayTvSeries() {
        ApiClient.getAiringTodayTvSeries().enqueue(object : Callback<TvSeriesFeed> {
            override fun onResponse(
                call: retrofit2.Call<TvSeriesFeed>,
                response: retrofit2.Response<TvSeriesFeed>
            ) {
                Log.d("", response.body().toString())
                airingToday.postValue(response.body()?.results)
            }

            override fun onFailure(call: retrofit2.Call<TvSeriesFeed>, t: Throwable) {
                airingToday.postValue(null)
            }
        })
    }

    fun getTopRatedTvSeries() {
        ApiClient.getTopRatedTvSeries().enqueue(object : Callback<TvSeriesFeed> {
            override fun onResponse(
                call: retrofit2.Call<TvSeriesFeed>,
                response: retrofit2.Response<TvSeriesFeed>
            ) {
                Log.d("", response.body().toString())
                topRated.postValue(response.body()?.results)
            }

            override fun onFailure(call: retrofit2.Call<TvSeriesFeed>, t: Throwable) {
                topRated.postValue(null)
            }
        })
    }

    fun getPopularTvSeries() {
        ApiClient.getPopularTvSeries().enqueue(object : Callback<TvSeriesFeed> {
            override fun onResponse(
                call: retrofit2.Call<TvSeriesFeed>,
                response: retrofit2.Response<TvSeriesFeed>
            ) {
                Log.d("", response.body().toString())
                popularTvSeries.postValue(response.body()?.results)
            }

            override fun onFailure(call: retrofit2.Call<TvSeriesFeed>, t: Throwable) {
                popularTvSeries.postValue(null)
            }
        })
    }

    fun getTvGenres() {
        ApiClient.getTvGenres().enqueue(object : Callback<MovieGenresFeed> {
            override fun onResponse(
                call: retrofit2.Call<MovieGenresFeed>,
                response: retrofit2.Response<MovieGenresFeed>
            ) {
                Log.d("", response.body().toString())
                tvSeriesGenres.postValue(response.body()?.genres)
            }

            override fun onFailure(call: retrofit2.Call<MovieGenresFeed>, t: Throwable) {
                tvSeriesGenres.postValue(null)
            }
        })
    }
}