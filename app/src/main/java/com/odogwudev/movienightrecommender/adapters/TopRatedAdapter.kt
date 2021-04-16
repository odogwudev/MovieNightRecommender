package com.odogwudev.movienightrecommender.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.listeners.ItemClickListener
import com.odogwudev.movienightrecommender.models.EmptyModel
import com.odogwudev.movienightrecommender.models.LocalModel
import com.odogwudev.movienightrecommender.models.Movies
import com.odogwudev.movienightrecommender.models.TvSeries


class TopRatedAdapter(val listener: ItemClickListener) :
    ListAdapter<LocalModel, RecyclerView.ViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.top_rated_item_row -> {
                TopRatedViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(viewType, parent, false)
                )
            }

            R.layout.popular_item_row -> {
                PopularMoviesViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(viewType, parent, false)
                )
            }

            R.layout.holder_shimmer_top_rated -> {
                ShimmerViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(viewType, parent, false)
                )
            }
            else -> AiringTvSeriesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false)
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TopRatedViewHolder -> holder.bind(getItem(position), listener)
            is PopularMoviesViewHolder -> holder.bind(getItem(position), listener)
            is ShimmerViewHolder -> holder.present(getItem(position))
            else -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Movies -> R.layout.top_rated_item_row
            is TvSeries -> R.layout.popular_item_row
            is EmptyModel -> R.layout.holder_shimmer_top_rated
            else -> R.layout.popular_item_row
        }
    }
}