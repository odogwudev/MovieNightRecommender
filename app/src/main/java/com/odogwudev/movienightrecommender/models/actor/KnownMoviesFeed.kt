package com.odogwudev.movienightrecommender.models.actor

import com.google.gson.annotations.SerializedName
import com.odogwudev.movienightrecommender.models.LocalModel

class KnownMoviesFeed(

    @SerializedName("cast")
    val knownMovies: List<KnownMovies>
) : LocalModel