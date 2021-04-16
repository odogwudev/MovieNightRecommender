package com.odogwudev.movienightrecommender.models.cast

import com.odogwudev.movienightrecommender.models.LocalModel

class MovieCastFeed(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
) : LocalModel