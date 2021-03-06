package com.odogwudev.movienightrecommender.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.listeners.GenresClickListener
import com.odogwudev.movienightrecommender.models.EmptyModel
import com.odogwudev.movienightrecommender.models.MovieGenres

class GenresAdapter(val listener: GenresClickListener) :
    androidx.recyclerview.widget.ListAdapter<Any, RecyclerView.ViewHolder>(DiffUtilClass<Any>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            R.layout.genres_item_row -> {
                return GenresViewHolder(
                    LayoutInflater.from(parent.context).inflate(viewType, parent, false)
                )
            }
            R.layout.holder_shimmer_genres -> {
                return ShimmerViewHolder(
                    LayoutInflater.from(parent.context).inflate(viewType, parent, false)
                )
            }
            else -> {
                return GenresViewHolder(
                    LayoutInflater.from(parent.context).inflate(viewType, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is GenresViewHolder -> {
                holder.present(getItem(position) as MovieGenres, listener)
            }

            is ShimmerViewHolder -> {
                holder.present(getItem(position) as EmptyModel)
            }

            else -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is MovieGenres -> {
                R.layout.genres_item_row
            }
            is EmptyModel -> {
                R.layout.holder_shimmer_genres
            }
            else -> {
                R.layout.genres_item_row
            }
        }

}