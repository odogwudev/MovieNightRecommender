package com.odogwudev.movienightrecommender.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.listeners.SearchItemClickListener
import com.odogwudev.movienightrecommender.models.EmptyModel
import com.odogwudev.movienightrecommender.models.LocalModel
import com.odogwudev.movienightrecommender.ui.dashboard.fragments.search.models.Query
import com.odogwudev.movienightrecommender.ui.dashboard.fragments.search.models.SearchItem

class SearchAdapter(
    private val listener: SearchItemClickListener
) : ListAdapter<LocalModel, RecyclerView.ViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.search_item_row -> {
                SearchViewHolder(
                    LayoutInflater.from(parent.context).inflate(viewType, parent, false)
                )
            }
            R.layout.query_item_row -> {
                QueryViewHolder(
                    LayoutInflater.from(parent.context).inflate(viewType, parent, false)
                )
            }
            R.layout.holder_shimmer_search_row -> {
                ShimmerViewHolder(
                    LayoutInflater.from(parent.context).inflate(viewType, parent, false)
                )
            }
            else -> {
                QueryViewHolder(
                    LayoutInflater.from(parent.context).inflate(viewType, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchViewHolder -> {
                holder.bind(getItem(position), listener)
            }
            is QueryViewHolder -> {
                holder.present(getItem(position), listener)
            }
            is ShimmerViewHolder -> {
                holder.present(getItem(position))
            }
            else -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SearchItem -> R.layout.search_item_row
            is Query -> R.layout.query_item_row
            is EmptyModel -> R.layout.holder_shimmer_search_row
            else -> R.layout.query_item_row
        }
    }

}