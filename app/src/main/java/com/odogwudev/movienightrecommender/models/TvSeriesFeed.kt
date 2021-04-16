package com.odogwudev.movienightrecommender.models

data class TvSeriesFeed(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<TvSeries>
) : LocalModel