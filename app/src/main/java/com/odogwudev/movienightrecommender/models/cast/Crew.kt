package com.odogwudev.movienightrecommender.models.cast

import com.odogwudev.movienightrecommender.models.LocalModel


class Crew(
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val name: String,
    val profile_path: String
) : LocalModel