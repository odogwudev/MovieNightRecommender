package com.odogwudev.movienightrecommender.listeners

import com.odogwudev.movienightrecommender.models.MovieGenres

interface GenresClickListener {
    fun onGenreClick(movieGenres: MovieGenres)
}