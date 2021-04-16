package com.odogwudev.movienightrecommender.network

import com.odogwudev.movienightrecommender.models.MovieGenresFeed
import com.odogwudev.movienightrecommender.models.ReviewsFeed
import com.odogwudev.movienightrecommender.models.TvSeriesFeed
import com.odogwudev.movienightrecommender.models.UpcomingMovies
import com.odogwudev.movienightrecommender.models.actor.Actor
import com.odogwudev.movienightrecommender.models.actor.KnownMoviesFeed
import com.odogwudev.movienightrecommender.models.cast.MovieCastFeed
import com.odogwudev.movienightrecommender.ui.dashboard.fragments.search.models.SearchItemsFeed
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path

object ApiClient {

    private  val BASE_URL = "https://api.themoviedb.org/3/"
    private val api: MovieNightApi

    init {
        api = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieNightApi::class.java)
    }

    fun getCountries(): Call<UpcomingMovies> {
        return api.getUpcomindMovies()
    }

    fun getTrendingMovies(): Call<UpcomingMovies> {
        return api.getTrendingMovies()
    }

    fun getMovieGenres(): Call<MovieGenresFeed> {
        return api.getMovieGenres()
    }

    fun getTopRatedMovies(): Call<UpcomingMovies> {
        return api.getTopRatedMovies()
    }

    fun getQueryInfo(query: String): Call<SearchItemsFeed> {
        return api.getQueryInfo(query)
    }

    fun getFeaturedTvSeries(): Call<TvSeriesFeed> {
        return api.getFeaturedTvSeries()
    }

    fun getAiringTodayTvSeries(): Call<TvSeriesFeed> {
        return api.getAiringTodayTvSeries()
    }

    fun getTopRatedTvSeries(): Call<TvSeriesFeed> {
        return api.getTopRatedTvSeries()
    }

    fun getPopularTvSeries(): Call<TvSeriesFeed> {
        return api.getPopularTvSeries()
    }

    fun getTvGenres(): Call<MovieGenresFeed> {
        return api.getTvGenres()
    }

    fun getResultsForThisGenre(query: Int): Call<UpcomingMovies> {
        return api.getResultsForThisGenre(query)
    }

    fun getMovieCastInfo(movieId: Int): Call<MovieCastFeed> {
        return api.getMovieCastInfo(movieId)
    }

    fun getMovieReviews(movieId: Int): Call<ReviewsFeed> {
        return api.getMovieReviews(movieId)
    }

    fun getTvCastInfo(tvSeriesId: Int): Call<MovieCastFeed> {
        return api.getTvCastInfo(tvSeriesId)
    }

    fun getTvReviews(tvSeriesId: Int): Call<ReviewsFeed> {
        return api.getTvReviews(tvSeriesId)
    }

    fun getActorInfo(actorId: Int): Call<Actor> {
        return api.getActorInfo(actorId)
    }

    fun getActorsKnownMovies(actorId: Int): Call<KnownMoviesFeed> {
        return api.getActorsKnownMovies(actorId)
    }
}