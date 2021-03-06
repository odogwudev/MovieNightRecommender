package com.odogwudev.movienightrecommender.models

class FavoriteTvSeries(
    val id: Int,
    val photo: String,
    val title: String,
    val movie_rating: Double,
    val description: String,
    val releaseDate: String
) : LocalModel {
    constructor() : this(0, "", "", 0.0, "", "")
}