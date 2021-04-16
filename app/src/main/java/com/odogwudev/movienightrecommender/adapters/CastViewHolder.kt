package com.odogwudev.movienightrecommender.adapters


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.listeners.LocalClickListener
import com.odogwudev.movienightrecommender.models.LocalModel
import com.odogwudev.movienightrecommender.models.cast.Cast
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.cast_item_row.view.*

class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun present(localModel: LocalModel, callback: LocalClickListener) {
        when (localModel) {
            is Cast -> {
                if (localModel.profile_path.isNullOrBlank()) {
                    itemView.castImg.setImageResource(R.drawable.profile_img_placeholder)
                } else {
                    Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500" + localModel.profile_path)
                        .placeholder(R.drawable.default_img)
                        .into(itemView.castImg)
                }

                itemView.castName.text = localModel.name

                itemView.setOnClickListener { callback.onCastClick(localModel) }
            }
        }
    }
}