package com.odogwudev.movienightrecommender.adapters

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.listeners.ItemClickListener
import com.odogwudev.movienightrecommender.models.LocalModel
import com.odogwudev.movienightrecommender.models.Movies
import com.odogwudev.movienightrecommender.models.TvSeries
import kotlinx.android.synthetic.main.upcoming_movies_item_row.view.*

class UpcomingMoviesViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(localModel: LocalModel, listener: ItemClickListener) {
        when (localModel) {
            is Movies -> {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + localModel.poster_path)
                    .placeholder(R.drawable.default_img)
                    .into(itemView.upcom_movieImg)

                itemView.ratingStars.rating = localModel.vote_average.toFloat() / 2

                itemView.upcom_movie_title.text = localModel.title
                itemView.setOnClickListener {
                    listener.onItemClick(localModel)
                }
            }

            is TvSeries -> {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + localModel.poster_path)
                    .placeholder(R.drawable.default_img)
                    .into(itemView.upcom_movieImg)

                itemView.upcom_movie_title.text = localModel.name

                itemView.setOnClickListener {
                    listener.onTvSeriesClick(localModel)
                }
            }
        }
    }
}