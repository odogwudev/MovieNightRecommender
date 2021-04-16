package com.odogwudev.movienightrecommender.ui.movieInfoScreen

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.abstraction.AbstractActivity
import com.odogwudev.movienightrecommender.databinding.ActivityMovieInfoScreenBinding
import com.odogwudev.movienightrecommender.listeners.LocalClickListener
import com.odogwudev.movienightrecommender.models.FavoriteMovies
import com.odogwudev.movienightrecommender.models.cast.Cast
import com.odogwudev.movienightrecommender.ui.castDetails.CastDetailsActivity

class MovieInfoScreen : AbstractActivity(), LocalClickListener {

    private var movieId: Int = 0
    private lateinit var moviePhoto: String
    private lateinit var movieTitle: String
    private lateinit var movieRating: String
    private lateinit var movieReleaseDate: String
    private lateinit var movieDescription: String

    private lateinit var binding: ActivityMovieInfoScreenBinding
    private lateinit var viewModel: MovieInfoScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieInfoScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initLayout() {
        viewModel = ViewModelProvider(this).get(MovieInfoScreenViewModel::class.java)
    }

    override fun running() {
        getIntentInfo()

        viewModel.initListener(this)
        viewModel.getFavoritesFromDb(movieTitle)

        viewModel.getMovieCastInfo(movieId)
        viewModel.getMovieReviews(movieId)

        binding.castRecView.adapter = viewModel.adapter
        binding.reviewsRecView.adapter = viewModel.reviewsAdapter

        binding.likeBtn.setOnClickListener {
            val whiteFavBtnColor: Drawable.ConstantState? =
                resources.getDrawable(R.drawable.ic_favorite_icon, this.theme).constantState
            val likeBtnColor = binding.likeBtn.drawable.constantState

            if (likeBtnColor!!.equals(whiteFavBtnColor)) {
                val fav = FavoriteMovies(
                    movieId,
                    moviePhoto,
                    movieTitle,
                    movieRating.toDouble(),
                    movieDescription,
                    movieReleaseDate
                )
                viewModel.addToFavorites(fav)
            } else {
                viewModel.removeFromFavorites(movieTitle)
            }
        }

        viewModel.observeData(this)

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

        binding.shareBtn.setOnClickListener {
            share()
        }
    }

    override fun stopped() {
        viewModel.removeObservers(this)
        viewModel.isFavorite.removeObservers(this)
    }

    private fun getIntentInfo() {
        movieId = intent.getIntExtra("MOVIE_ID", movieId)
        moviePhoto = intent.getStringExtra("MOVIE_PHOTO").toString()

        movieTitle = intent.getStringExtra("MOVIE_NAME") ?: ""
        binding.mainTxt.text = movieTitle

        movieDescription = intent.getStringExtra("DESCRIPTION") ?: ""
        binding.description.text = movieDescription

        movieReleaseDate = intent.getStringExtra("RELEASE_DATE") ?: ""
        binding.releaseDate.text = movieReleaseDate

        movieRating = intent.getStringExtra("RATING").toString()
        var rating = intent.getStringExtra("RATING")?.toDouble()
        if (rating != null) {
            binding.ratingBar.rating = rating.toFloat() / 2
        }

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500$moviePhoto")
            .placeholder(R.drawable.default_img)
            .into(binding.imgView)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500$moviePhoto")
            .placeholder(R.drawable.default_img)
            .into(binding.movieImg)
    }

    private fun share() {
        startActivity(Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, movieTitle)
        })
    }

    override fun onCastClick(cast: Cast) {
        startActivity(Intent(this, CastDetailsActivity::class.java).putExtra("ACTOR_ID", cast.id))
    }
}