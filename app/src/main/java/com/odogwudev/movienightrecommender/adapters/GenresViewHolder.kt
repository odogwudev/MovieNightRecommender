package com.odogwudev.movienightrecommender.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.listeners.GenresClickListener
import com.odogwudev.movienightrecommender.models.LocalModel
import com.odogwudev.movienightrecommender.models.MovieGenres
import com.odogwudev.movienightrecommender.utils.ColorHelper
import kotlinx.android.synthetic.main.genres_item_row.view.*


class GenresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun present(localModel : LocalModel, listener: GenresClickListener) {
        when(localModel){
            is MovieGenres -> {
                itemView.genres.text = localModel.name

                ColorHelper.getAllColors().forEach {
                    if(localModel.name == it.name) {
                        itemView.genres.backgroundTintList = ColorStateList.valueOf(Color.parseColor(it.color))
                    }
                }

                itemView.setOnClickListener{
                    listener.onGenreClick(localModel)
                }
            }
        }
    }

}