package com.odogwudev.movienightrecommender.ui.tvSeriesInfoScreen


import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.odogwudev.movienightrecommender.adapters.CastAdapter
import com.odogwudev.movienightrecommender.adapters.ReviewsAdapter
import com.odogwudev.movienightrecommender.listeners.LocalClickListener
import com.odogwudev.movienightrecommender.models.EmptyModel
import com.odogwudev.movienightrecommender.models.FavoriteTvSeries
import com.odogwudev.movienightrecommender.models.LocalModel
import com.odogwudev.movienightrecommender.models.cast.Cast

class TvSeriesInfoScreenViewModel : ViewModel(), LocalClickListener {

    private val repo by lazy { TvSeriesInfoRepository() }
    val castAdapter by lazy { CastAdapter(this) }
    val reviewsAdapter by lazy { ReviewsAdapter() }
    private var cast = repo.cast
    private var reviews = repo.reviews
    val isFavorite = repo.isFavorite
    private lateinit var callback: LocalClickListener

    init {
        castAdapter.submitList(
            mutableListOf(
                EmptyModel(""),
                EmptyModel(""),
                EmptyModel(""),
                EmptyModel(""),
                EmptyModel(""),
                EmptyModel(""),
                EmptyModel("")
            ) as List<LocalModel>?
        )
    }

    fun observeDataFromApi(owner: LifecycleOwner, callback: LocalClickListener) {
        this.callback = callback
        cast.observe(owner, Observer { cast ->
            Log.d("cast is:", cast.toString())
            castAdapter.submitList(cast)
            castAdapter.notifyDataSetChanged()
        })

        reviews.observe(owner, Observer { reviews ->
            Log.d("reviews:", reviews.toString())
            reviewsAdapter.submitList(reviews)
        })
    }

    fun removeObservers(owner: LifecycleOwner) {
        cast.removeObservers(owner)
        reviews.removeObservers(owner)
    }

    fun getCastInfo(tvSeriesId: Int) {
        repo.getCastInfo(tvSeriesId)
    }

    fun getTvSeriesReviews(tvSeriesId: Int) {
        repo.getTvSeriesReviews(tvSeriesId)
    }

    fun addToFavorites(favorite: FavoriteTvSeries) {
        repo.addToFavorites(favorite)
    }

    fun removeFromFavorites(tvSeriesId: Int) {
        repo.removeFromFavorites(tvSeriesId)
    }

    fun getUserFavorites(tvSeriesId: Int) {
        repo.getUserFavorites(tvSeriesId)
    }

    override fun onCastClick(cast: Cast) {
        callback.onCastClick(cast)
    }
}