package com.odogwudev.movienightrecommender.ui.castDetails

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.odogwudev.movienightrecommender.ui.castDetails.adapter.KnownMoviesAdapter


class CastDetailsViewModel : ViewModel() {

    private val repo = CastDetailsRepository()
    val actorData = repo.actorData
    val knownMovies = repo.knownMovies
    val adapter = KnownMoviesAdapter()

    fun getActorData(actorID: Int) {
        repo.getActorsData(actorID)
    }

    fun getActorsKnownMovies(actorID: Int) {
        repo.getActorsKnownMovies(actorID)
    }

    fun observeData(owner: LifecycleOwner) {
        knownMovies.observe(owner, Observer {
            Log.d("", it.toString())
            adapter.submitList(it.knownMovies)
            adapter.notifyDataSetChanged()
        })
    }

    fun removeObservers(owner: LifecycleOwner) {
        knownMovies.removeObservers(owner)
    }
}