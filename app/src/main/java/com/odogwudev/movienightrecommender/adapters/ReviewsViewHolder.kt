package com.odogwudev.movienightrecommender.adapters

import androidx.recyclerview.widget.RecyclerView
import com.odogwudev.movienightrecommender.databinding.ReviewsItemRowBinding
import com.odogwudev.movienightrecommender.models.LocalModel
import com.odogwudev.movienightrecommender.models.Reviews

class ReviewsViewHolder(var binding: ReviewsItemRowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun present(localModel: LocalModel) {
        when (localModel) {
            is Reviews -> {
                binding.author.text = localModel.author
                binding.searchType.text = localModel.content
            }
        }
    }
}