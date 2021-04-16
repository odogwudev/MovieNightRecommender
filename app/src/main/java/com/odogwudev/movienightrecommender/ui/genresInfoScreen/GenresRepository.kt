package com.odogwudev.movienightrecommender.ui.genresInfoScreen


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.odogwudev.movienightrecommender.models.Movies
import com.odogwudev.movienightrecommender.models.UpcomingMovies
import com.odogwudev.movienightrecommender.network.ApiClient
import retrofit2.Callback

class GenresRepository {

    val movies = MutableLiveData<List<Movies>>()

    fun getResultsForThisGenre(genreId: Int) {
        ApiClient.getResultsForThisGenre(genreId).enqueue(object : Callback<UpcomingMovies> {
            override fun onResponse(
                call: retrofit2.Call<UpcomingMovies>,
                response: retrofit2.Response<UpcomingMovies>
            ) {
                Log.d("", response.body().toString())
                movies.postValue(response.body()?.results)
            }

            override fun onFailure(call: retrofit2.Call<UpcomingMovies>, t: Throwable) {
                movies.postValue(null)
            }
        })
    }
}