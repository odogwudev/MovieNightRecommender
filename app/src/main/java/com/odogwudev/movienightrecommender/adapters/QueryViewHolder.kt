package com.odogwudev.movienightrecommender.adapters


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.listeners.SearchItemClickListener
import com.odogwudev.movienightrecommender.models.LocalModel
import com.odogwudev.movienightrecommender.ui.dashboard.fragments.search.models.Query
import kotlinx.android.synthetic.main.query_item_row.view.*

class QueryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun present(localModel: LocalModel, listener: SearchItemClickListener) {
        when (localModel) {
            is Query -> {
                itemView.queryName.text = localModel.queryName

                itemView.setOnClickListener {
                    listener.onQueryClick(localModel)
                }
            }
        }
    }
}