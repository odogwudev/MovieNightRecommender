package com.odogwudev.movienightrecommender.ui.userProfile.holder


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.adapters.DiffUtilClass
import com.odogwudev.movienightrecommender.adapters.ShimmerViewHolder
import com.odogwudev.movienightrecommender.databinding.PopularItemRowBinding
import com.odogwudev.movienightrecommender.listeners.FavoriteClickListener
import com.odogwudev.movienightrecommender.models.EmptyModel
import com.odogwudev.movienightrecommender.models.FavoriteMovies
import com.odogwudev.movienightrecommender.models.FavoriteTvSeries

class FavoriteAdapter(
    private val listener: FavoriteClickListener
) : ListAdapter<Any, RecyclerView.ViewHolder>(DiffUtilClass<Any>()) {

    val FAVORITE_MOVIES = 1
    val FAVORITE_TVSERIES = 2
    val EMPTY_MODEL = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            FAVORITE_MOVIES -> {
                return FavoriteMoviesViewHolder(
                    PopularItemRowBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }
            FAVORITE_TVSERIES -> {
                return FavoriteTvSeriesViewHolder(
                    PopularItemRowBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }
            EMPTY_MODEL -> {
                return ShimmerViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.holder_shimmer_popular, parent, false)
                )
            }
            else -> {
                return ShimmerViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.holder_shimmer_popular, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position)) {
            is FavoriteMovies -> {
                (holder as FavoriteMoviesViewHolder).bind(
                    getItem(position) as FavoriteMovies,
                    listener
                )
            }
            is FavoriteTvSeries -> {
                (holder as FavoriteTvSeriesViewHolder).bind(
                    getItem(position) as FavoriteTvSeries,
                    listener
                )
            }
            is EmptyModel -> {
                (holder as ShimmerViewHolder).present(getItem(position) as EmptyModel)
            }
            else -> {
                (holder as ShimmerViewHolder).present(
                    getItem(position) as EmptyModel
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is FavoriteMovies -> {
                FAVORITE_MOVIES
            }
            is FavoriteTvSeries -> {
                FAVORITE_TVSERIES
            }
            is EmptyModel -> {
                EMPTY_MODEL
            }
            else -> {
                4
            }
        }
    }
}