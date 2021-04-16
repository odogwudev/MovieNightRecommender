package com.odogwudev.movienightrecommender.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.listeners.ItemClickListener
import com.odogwudev.movienightrecommender.models.TvSeries


class AiringTvSeriesAdapter(private val listener: ItemClickListener) :
    ListAdapter<Any, PopularMoviesViewHolder>(DiffUtilClass<Any>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.popular_item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        holder.bind(getItem(position) as TvSeries, listener)
    }
}