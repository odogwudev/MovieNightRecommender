package com.odogwudev.movienightrecommender.ui.genresInfoScreen.adapters

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.listeners.ItemClickListener
import com.odogwudev.movienightrecommender.models.Movies
import kotlin.math.roundToInt

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val resultImg: ImageView = itemView.findViewById(R.id.movie_img)
    val resultTxt: TextView = itemView.findViewById(R.id.movie_title)
    val movieRating: TextView = itemView.findViewById(R.id.rating_movie_txt)
    val movieGenre: TextView = itemView.findViewById(R.id.movie_genre)
    val progressBar: ProgressBar = itemView.findViewById(R.id.rating_progressbar)

    fun bind(movies: Movies, listener: ItemClickListener) {
        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500" + movies.poster_path)
            .placeholder(R.drawable.default_img)
            .into(resultImg)

        if (movies.name.isNullOrBlank()) {
            resultTxt.text = movies.title
        } else {
            resultTxt.text = movies.name
        }

        movieGenre.text = movies.media_type
        movieRating.text = movies.vote_average.toString()
        progressBar.progress = (movies.vote_average * 10).roundToInt()

        itemView.setOnClickListener {
            listener.onItemClick(movies)
        }

    }
}