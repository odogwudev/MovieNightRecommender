package com.odogwudev.movienightrecommender.network

import com.odogwudev.movienightrecommender.API_KEY
import com.odogwudev.movienightrecommender.BASE_URL
import com.odogwudev.movienightrecommender.models.MovieGenresFeed
import com.odogwudev.movienightrecommender.models.ReviewsFeed
import com.odogwudev.movienightrecommender.models.TvSeriesFeed
import com.odogwudev.movienightrecommender.models.UpcomingMovies
import com.odogwudev.movienightrecommender.models.actor.Actor
import com.odogwudev.movienightrecommender.models.actor.KnownMoviesFeed
import com.odogwudev.movienightrecommender.models.cast.MovieCastFeed
import com.odogwudev.movienightrecommender.ui.dashboard.fragments.search.models.SearchItemsFeed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieNightApi {

    @GET("movie/upcoming?$API_KEY")
    fun getUpcomindMovies(): Call<UpcomingMovies>

    @GET("trending/movie/day?$API_KEY")
    fun getTrendingMovies(): Call<UpcomingMovies>

    @GET("genre/movie/list?$API_KEY")
    fun getMovieGenres(): Call<MovieGenresFeed>

    @GET("movie/top_rated?$API_KEY")
    fun getTopRatedMovies(): Call<UpcomingMovies>

    @GET("tv/on_the_air?$API_KEY")
    fun getFeaturedTvSeries(): Call<TvSeriesFeed>

    @GET("tv/airing_today?$API_KEY")
    fun getAiringTodayTvSeries(): Call<TvSeriesFeed>

    @GET("tv/top_rated?$API_KEY")
    fun getTopRatedTvSeries(): Call<TvSeriesFeed>

    @GET("tv/top_rated?$API_KEY&language=en-US")
    fun getPopularTvSeries(): Call<TvSeriesFeed>

    @GET("genre/tv/list?$API_KEY")
    fun getTvGenres(): Call<MovieGenresFeed>

    @GET("search/multi?$API_KEY")
    fun getQueryInfo(@Query("query") query: String): Call<SearchItemsFeed>

    @GET("discover/movie?$API_KEY&with_genres=")
    fun getResultsForThisGenre(@Query("queryId") queryId: Int): Call<UpcomingMovies>

    @GET("movie/{movieId}/credits?$API_KEY")
    fun getMovieCastInfo(@Path("movieId") movieId: Int): Call<MovieCastFeed>

    @GET("movie/{movieId}/reviews?$API_KEY")
    fun getMovieReviews(@Path("movieId") movieId: Int): Call<ReviewsFeed>

    @GET("tv/{tvSeriesId}/credits?$API_KEY")
    fun getTvCastInfo(@Path("tvSeriesId") tvSeriesId: Int): Call<MovieCastFeed>

    @GET("tv/{tvSeriesId}/reviews?$API_KEY")
    fun getTvReviews(@Path("tvSeriesId") tvSeriesId: Int): Call<ReviewsFeed>

    @GET("person/{actorId}?$API_KEY")
    fun getActorInfo(@Path("actorId") actorId: Int): Call<Actor>

    @GET("$BASE_URL/person/{actorId}/movie_credits?$API_KEY")
    fun getActorsKnownMovies(@Path("actorId") actorId: Int): Call<KnownMoviesFeed>
}