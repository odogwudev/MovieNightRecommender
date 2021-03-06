package com.odogwudev.movienightrecommender.adapters

import android.os.Parcel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.Shimmer
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.listeners.LocalClickListener
import com.odogwudev.movienightrecommender.models.EmptyModel
import com.odogwudev.movienightrecommender.models.LocalModel
import com.odogwudev.movienightrecommender.models.cast.Cast


class CastAdapter(val callback: LocalClickListener) :
    ListAdapter<LocalModel, RecyclerView.ViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            R.layout.cast_item_row -> {
                return CastViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(viewType, parent, false)
                )
            }
            R.layout.holder_shimmer_cast_item -> {
                return ShimmerViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(viewType, parent, false)
                )
            }
            else -> {
                return ShimmerViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(viewType, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CastViewHolder -> {
                holder.present(getItem(position), callback)
            }

            is ShimmerViewHolder -> {
                holder.present(getItem(position))
            }
            else -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)){
            is Cast -> {
                R.layout.cast_item_row
            }

            is EmptyModel -> {
                R.layout.holder_shimmer_cast_item
            }
            else -> {
                R.layout.cast_item_row
            }
        }
    }
}