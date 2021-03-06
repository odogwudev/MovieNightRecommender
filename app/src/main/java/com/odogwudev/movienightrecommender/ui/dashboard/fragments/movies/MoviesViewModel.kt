package com.odogwudev.movienightrecommender.ui.dashboard.fragments.movies


import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.odogwudev.movienightrecommender.adapters.*
import com.odogwudev.movienightrecommender.listeners.GenresClickListener
import com.odogwudev.movienightrecommender.listeners.ItemClickListener
import com.odogwudev.movienightrecommender.models.*

class MoviesViewModel : ViewModel(), ItemClickListener, GenresClickListener {

    private val repo = MoviesRepository()
    private var upcomingMovies = repo.upcomingMovies
    private var trendingMovies = repo.trendingMovies
    private var movieGenres = repo.movieGenres
    private var topRatedMovies = repo.topRatedMovies
    private lateinit var emptyModelList: MutableList<EmptyModel>

    private lateinit var listener: ItemClickListener
    private lateinit var genresListener: GenresClickListener

    val upcomingAdapter = UpcomingAdapter(this)
    val trendingAdapter = TrendingAdapter(this)
    val topRatedAdapter = TopRatedAdapter(this)
    val genresAdapter = GenresAdapter(this)

    init {
        setShimmer()
    }

    fun setShimmer(): MutableList<EmptyModel> {
        emptyModelList = mutableListOf(
            EmptyModel(""),
            EmptyModel(""),
            EmptyModel(""),
            EmptyModel("")
        )
        return emptyModelList
    }

    fun initListeners(listener: ItemClickListener, genresListener: GenresClickListener) {
        this.listener = listener
        this.genresListener = genresListener
    }

    fun observeData(owner: LifecycleOwner) {
        upcomingMovies.observe(owner, Observer { t ->
            upcomingAdapter.submitList(t)
            upcomingAdapter.notifyDataSetChanged()
        })

        trendingMovies.observe(owner, Observer { t ->
            trendingAdapter.submitList(t)
            trendingAdapter.notifyDataSetChanged()
        })

        topRatedMovies.observe(owner, Observer { t ->
            //sorting list by rating and passing it to the adapter
            topRatedAdapter.submitList(t.sortedWith(compareBy { it.vote_average }).reversed())
            topRatedAdapter.notifyDataSetChanged()
            Log.d("SortedList", t.sortedWith(compareBy { it.vote_average }).reversed().toString())
        })

        movieGenres.observe(owner, Observer { t ->
            genresAdapter.submitList(t as List<Any>?)
            genresAdapter.notifyDataSetChanged()
        })
    }

    fun removeObservers(owner: LifecycleOwner) {
        upcomingMovies.removeObservers(owner)
        trendingMovies.removeObservers(owner)
        topRatedMovies.removeObservers(owner)
        movieGenres.removeObservers(owner)
    }

    fun getUpcomingMovies() {
        repo.getUpcomingMovies()
    }

    fun getTrendingMovies() {
        repo.getTrendingMovies()
    }

    fun getMovieGenres() {
        repo.getMovieGenres()
    }

    fun getTopRatedMovies() {
        repo.getTopRatedMovies()
    }

    override fun onItemClick(movies: Movies) {
        listener.onItemClick(movies)
    }

    override fun onTvSeriesClick(tvSeries: TvSeries) {
        listener.onTvSeriesClick(tvSeries)
    }

    override fun onGenreClick(movieGenres: MovieGenres) {
        genresListener.onGenreClick(movieGenres)
    }
}