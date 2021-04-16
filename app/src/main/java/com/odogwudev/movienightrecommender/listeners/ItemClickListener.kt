package com.odogwudev.movienightrecommender.listeners

import com.odogwudev.movienightrecommender.models.Movies
import com.odogwudev.movienightrecommender.models.TvSeries

interface ItemClickListener {
    fun onItemClick(movies: Movies)
    fun onTvSeriesClick(tvSeries: TvSeries)
}