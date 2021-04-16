package com.odogwudev.movienightrecommender.models

data class MovieGenresFeed(
    val genres: List<MovieGenres>
) : LocalModel