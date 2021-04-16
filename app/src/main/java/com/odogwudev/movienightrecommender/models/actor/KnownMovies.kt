package com.odogwudev.movienightrecommender.models.actor

import com.google.gson.annotations.SerializedName
import com.odogwudev.movienightrecommender.models.LocalModel

class KnownMovies(

    val character: String,

    @SerializedName("original_title")
    val title: String,

    @SerializedName("poster_path")
    val photo: String,

    val backdrop_path: String,
    val vote_average: Double
) : LocalModel