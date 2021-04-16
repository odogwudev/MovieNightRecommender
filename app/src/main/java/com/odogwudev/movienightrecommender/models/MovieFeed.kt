package com.odogwudev.movienightrecommender.models

data class MovieFeed(
    val page: Int,
    val results: List<Movies>
) : LocalModel