package com.odogwudev.movienightrecommender.ui.dashboard.fragments.search


import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.odogwudev.movienightrecommender.network.ApiClient
import com.odogwudev.movienightrecommender.ui.dashboard.fragments.search.models.Query
import com.odogwudev.movienightrecommender.ui.dashboard.fragments.search.models.SearchItem
import com.odogwudev.movienightrecommender.ui.dashboard.fragments.search.models.SearchItemsFeed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository {

    private var userQueries: MutableList<Query> = mutableListOf()
    private val databaseReference = FirebaseDatabase.getInstance().reference
    val recentSearches = MutableLiveData<MutableList<SearchItem>>()
    val queries = MutableLiveData<MutableList<Query>>()

    fun getQueryInfo(query: Query) {
        ApiClient.getQueryInfo(query.queryName).enqueue(object : Callback<SearchItemsFeed>{
            override fun onResponse(
                call: Call<SearchItemsFeed>,
                response: Response<SearchItemsFeed>
            ) {
                recentSearches.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<SearchItemsFeed>, t: Throwable) {
                recentSearches.postValue(null)
            }
        })
    }

    fun addQueryToDb(query: Query) {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userSearchQueries")
            .child(query.queryName).setValue(query)
    }

    fun getRecentUserQueries() {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userSearchQueries")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    //
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()) {
                        p0.children.forEach {
                            val query = it.getValue(Query::class.java)
                            userQueries.add(query!!)
                        }
                    }
                    queries.postValue(userQueries)
                }
            })
    }
}