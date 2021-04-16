package com.odogwudev.movienightrecommender.models

data class UpcomingMovies(
    val results: List<Movies>
) : LocalModel