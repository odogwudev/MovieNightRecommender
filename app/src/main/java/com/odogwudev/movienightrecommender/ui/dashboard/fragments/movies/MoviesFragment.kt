package com.odogwudev.movienightrecommender.ui.dashboard.fragments.movies

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.odogwudev.movienightrecommender.abstraction.AbstractFragment
import com.odogwudev.movienightrecommender.databinding.FragmentMoviesBinding
import com.odogwudev.movienightrecommender.ui.genresInfoScreen.GenresInfoScreen
import com.odogwudev.movienightrecommender.models.*
import com.odogwudev.movienightrecommender.ui.movieInfoScreen.MovieInfoScreen
import com.odogwudev.movienightrecommender.listeners.GenresClickListener
import com.odogwudev.movienightrecommender.listeners.ItemClickListener


class MoviesFragment : AbstractFragment(), ItemClickListener, GenresClickListener {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var binding: FragmentMoviesBinding

    override fun created(): View? {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initLayout(view: View) {
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
    }

    override fun running() {
        setShimmer()

        viewModel.initListeners(this,this)

        binding.upcomingMoviesRecView.adapter = viewModel.upcomingAdapter
        binding.popularRecView.adapter = viewModel.trendingAdapter
        binding.topRatedRecView.adapter = viewModel.topRatedAdapter
        binding.genresRecView.adapter = viewModel.genresAdapter

        callApiForResults()
        viewModel.observeData(this)
    }

    override fun stop() {
        viewModel.removeObservers(this)
    }

    private fun callApiForResults() {
        viewModel.getUpcomingMovies()
        viewModel.getMovieGenres()
        viewModel.getTopRatedMovies()
        viewModel.getTrendingMovies()
    }

    private fun setShimmer() {
        viewModel.upcomingAdapter.submitList(viewModel.setShimmer() as List<LocalModel>?)
        viewModel.trendingAdapter.submitList(viewModel.setShimmer() as List<LocalModel>?)
        viewModel.topRatedAdapter.submitList(viewModel.setShimmer() as List<LocalModel>?)
        viewModel.genresAdapter.submitList(viewModel.setShimmer() as List<Any>?)
    }

    override fun onItemClick(movies: Movies) {
        startActivity(Intent(activity, MovieInfoScreen::class.java).apply {
            if (movies.name.isNullOrBlank()) {
                putExtra("MOVIE_NAME", movies.title)
                Log.d("Movie Name Clicked", movies.title)
            } else {
                putExtra("MOVIE_NAME", movies.name)
                Log.d("Movie Name Clicked", movies.name)
            }
            putExtra("MOVIE_ID", movies.id)
            putExtra("MOVIE_PHOTO", movies.backdrop_path)
            putExtra("MOVIE_PHOTO", movies.poster_path)
            putExtra("RELEASE_DATE", movies.release_date)
            putExtra("DESCRIPTION", movies.overview)
            putExtra("RATING", movies.vote_average.toString())
        })
    }

    override fun onTvSeriesClick(tvSeries: TvSeries) {
        // N/A Here
    }

    override fun onGenreClick(movieGenres: MovieGenres) {
        startActivity(Intent(activity, GenresInfoScreen::class.java).apply {
            putExtra("GENRE_ID", movieGenres.id)
            putExtra("GENRE_NAME", movieGenres.name)
        })
    }
}