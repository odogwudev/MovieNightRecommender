package com.odogwudev.movienightrecommender.listeners

import com.odogwudev.movienightrecommender.models.cast.Cast

interface LocalClickListener {

    fun onCastClick(cast: Cast)
}