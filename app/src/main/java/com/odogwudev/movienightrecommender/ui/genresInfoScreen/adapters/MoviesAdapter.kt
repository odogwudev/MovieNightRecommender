package com.odogwudev.movienightrecommender.ui.genresInfoScreen.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.adapters.DiffUtilClass
import com.odogwudev.movienightrecommender.listeners.ItemClickListener
import com.odogwudev.movienightrecommender.adapters.ShimmerViewHolder
import com.odogwudev.movienightrecommender.models.EmptyModel
import com.odogwudev.movienightrecommender.models.Movies
import com.odogwudev.movienightrecommender.models.TvSeries

class MoviesAdapter(private var listener: ItemClickListener) :
    ListAdapter<Any, RecyclerView.ViewHolder>(DiffUtilClass<Any>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                MoviesViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.movie_item_row, parent, false)
                )
            }
            2 -> {
                TvSeriesViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.movie_item_row, parent, false)
                )
            }
            R.layout.holder_shimmer_genres_item -> {
                ShimmerViewHolder(
                    LayoutInflater.from(parent.context).inflate(viewType, parent, false)
                )
            }
            else -> {
                ShimmerViewHolder(
                    LayoutInflater.from(parent.context).inflate(viewType, parent, false)
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position)) {
            is Movies -> {
                (holder as MoviesViewHolder).bind(getItem(position) as Movies, listener)
            }
            is TvSeries -> {
                (holder as TvSeriesViewHolder).bind(getItem(position) as TvSeries, listener)
            }
            is EmptyModel -> {
                (holder as ShimmerViewHolder).present(getItem(position) as EmptyModel)
            }
            else -> {
                (holder as ShimmerViewHolder).present(getItem(position) as EmptyModel)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Movies -> {
                1
            }
            is TvSeries -> {
                2
            }
            is EmptyModel -> {
                R.layout.holder_shimmer_genres_item
            }
            else -> {
                R.layout.holder_shimmer_genres_item
            }
        }
    }
}