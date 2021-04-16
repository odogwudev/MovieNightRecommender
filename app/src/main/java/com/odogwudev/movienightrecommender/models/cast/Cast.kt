package com.odogwudev.movienightrecommender.models.cast

import com.odogwudev.movienightrecommender.models.LocalModel

class Cast(
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val id: Int,
    val name: String,
    val order: Int,
    val profile_path: String
) : LocalModel