package com.odogwudev.movienightrecommender.ui.genresInfoScreen


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.odogwudev.movienightrecommender.R
import com.odogwudev.movienightrecommender.abstraction.AbstractActivity
import com.odogwudev.movienightrecommender.databinding.ActivityGenresInfoScreenBinding
import com.odogwudev.movienightrecommender.ui.movieInfoScreen.MovieInfoScreen

class GenresInfoScreen : AbstractActivity() {

    private var genreId: Int = 0
    private lateinit var genreName: String
    private lateinit var binding: ActivityGenresInfoScreenBinding
    private lateinit var viewModel: GenresInfoScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenresInfoScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initLayout() {
        viewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(GenresInfoScreenViewModel::class.java)
    }

    override fun running() {
        genreId = intent.getIntExtra("GENRE_ID", genreId)
        genreName = intent.getStringExtra("GENRE_NAME") ?: ""

        viewModel.getResultsForThisGenre(genreId)

        viewModel.getBackgroundColor(genreName)
        binding.genresHeaderTxt.text = resources.getString(R.string.genres_header_title, genreName)

        binding.genresGridRecView.adapter = viewModel.adapter

        observeViewModel()
    }

    override fun stopped() {
        viewModel.removeObservers(this)
    }

    private fun observeViewModel() {
        viewModel.observeData(this)

        viewModel.headerColor.observe(this, Observer {
            binding.genresHeaderTxt.setBackgroundColor(Color.parseColor(it))
        })

        viewModel.movie.observe(this, Observer { movie ->
            startActivity(Intent(this, MovieInfoScreen::class.java).also {
                if (movie.name.isNullOrBlank()) {
                    it.putExtra("MOVIE_NAME", movie.title)
                } else {
                    it.putExtra("MOVIE_NAME", movie.name)
                }
                it.putExtra("MOVIE_ID", movie.id)
                it.putExtra("MOVIE_PHOTO", movie.backdrop_path)
                it.putExtra("MOVIE_PHOTO", movie.poster_path)
                it.putExtra("RELEASE_DATE", movie.release_date)
                it.putExtra("DESCRIPTION", movie.overview)
                it.putExtra("RATING", movie.vote_average.toString())
            })
        })
    }
}