package com.odogwudev.movienightrecommender.models

data class GenreMoviesFeed(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<Movies>
) : LocalModel