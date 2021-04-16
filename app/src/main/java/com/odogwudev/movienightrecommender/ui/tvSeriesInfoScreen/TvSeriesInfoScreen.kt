package com.odogwudev.movienightrecommender.ui.tvSeriesInfoScreen


import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.abstraction.AbstractActivity
import com.odogwudev.movienightrecommender.databinding.ActivityTvSeriesInfoScreenBinding
import com.odogwudev.movienightrecommender.listeners.LocalClickListener
import com.odogwudev.movienightrecommender.models.FavoriteTvSeries
import com.odogwudev.movienightrecommender.models.cast.Cast
import com.odogwudev.movienightrecommender.ui.castDetails.CastDetailsActivity

class TvSeriesInfoScreen : AbstractActivity() {

    private var tvSeriesId: Int = 0
    private lateinit var tvSeriesPhoto: String
    private lateinit var tvSeriesTitle: String
    private lateinit var tvSeriesRating: String
    private lateinit var tvSeriesReleaseDate: String
    private lateinit var tvSeriesDescription: String
    private lateinit var binding: ActivityTvSeriesInfoScreenBinding
    private lateinit var viewModel: TvSeriesInfoScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvSeriesInfoScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initLayout() {
        viewModel = TvSeriesInfoScreenViewModel()
    }

    override fun running() {
        getIntentInfo()

        viewModel.getUserFavorites(tvSeriesId)

        binding.castRecView.adapter = viewModel.castAdapter
        binding.reviewsRecView.adapter = viewModel.reviewsAdapter

        viewModel.getCastInfo(tvSeriesId)
        viewModel.getTvSeriesReviews(tvSeriesId)

        observeViewModel()

        binding.likeBtn.setOnClickListener {
            val whiteFavBtnColor: Drawable.ConstantState? =
                resources.getDrawable(R.drawable.ic_favorite_icon, this.theme).constantState
            val likeBtnColor = binding.likeBtn.drawable.constantState

            if (likeBtnColor!!.equals(whiteFavBtnColor)) {
                val fav = FavoriteTvSeries(
                    tvSeriesId,
                    tvSeriesPhoto,
                    tvSeriesTitle,
                    tvSeriesRating.toDouble(),
                    tvSeriesReleaseDate,
                    tvSeriesDescription
                )
                viewModel.addToFavorites(fav)
            } else {
                viewModel.removeFromFavorites(tvSeriesId)
            }
        }

        binding.shareBtn.setOnClickListener {
            share()
        }
    }

    private fun observeViewModel() {
        viewModel.observeDataFromApi(this, object : LocalClickListener {
            override fun onCastClick(cast: Cast) {
                startActivity(Intent(applicationContext, CastDetailsActivity::class.java).putExtra("ACTOR_ID", cast.id))
            }
        })

        viewModel.isFavorite.observe(this, Observer {
            when (it) {
                true -> {
                    binding.likeBtn.setImageResource(R.drawable.ic_favorite_white)
                }

                false -> {
                    binding.likeBtn.setImageResource(R.drawable.ic_favorite_icon)
                }
            }
        })
    }

    override fun stopped() {
        viewModel.removeObservers(this)
        viewModel.isFavorite.removeObservers(this)
    }

    private fun getIntentInfo() {
        tvSeriesId = intent.getIntExtra("TV_SERIES_ID", tvSeriesId)
        tvSeriesPhoto = intent.getStringExtra("TV_SERIES_PHOTO").toString()
        tvSeriesTitle = intent.getStringExtra("TV_SERIES_NAME").toString()
        tvSeriesRating = intent.getStringExtra("TV_SERIES_RATING").toString()
        tvSeriesReleaseDate = intent.getStringExtra("TV_SERIES_RELEASE_DATE").toString()
        tvSeriesDescription = intent.getStringExtra("TV_SERIES_DESCRIPTION").toString()

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500$tvSeriesPhoto")
            .into(binding.posterImg)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500$tvSeriesPhoto")
            .into(binding.imgView)

        binding.tvSeriesTxt.text = tvSeriesTitle

        val rating = tvSeriesRating.toDouble()
        binding.ratingBar.rating = rating.toFloat() / 2

        binding.description.text = tvSeriesDescription
        binding.releaseDate.text = tvSeriesReleaseDate
    }

    private fun share() {
        startActivity(Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, tvSeriesTitle)
        })
    }
}