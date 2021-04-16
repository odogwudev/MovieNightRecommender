package com.odogwudev.movienightrecommender.listeners

import com.odogwudev.movienightrecommender.models.FavoriteMovies
import com.odogwudev.movienightrecommender.models.FavoriteTvSeries

interface FavoriteClickListener {

    fun onFavoriteMoviesClick(favoriteMovies: FavoriteMovies)
    fun onFavoriteTvSeriesClick(favoriteTvSeries: FavoriteTvSeries)
}