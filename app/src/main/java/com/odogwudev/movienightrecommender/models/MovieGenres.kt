package com.odogwudev.movienightrecommender.models

class MovieGenres(
    val id: Int,
    val name: String
) : LocalModel {
    constructor() : this(0, "")
}