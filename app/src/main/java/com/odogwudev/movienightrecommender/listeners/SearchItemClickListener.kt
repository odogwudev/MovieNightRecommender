package com.odogwudev.movienightrecommender.listeners

import com.odogwudev.movienightrecommender.ui.dashboard.fragments.search.models.Query
import com.odogwudev.movienightrecommender.ui.dashboard.fragments.search.models.SearchItem

interface SearchItemClickListener {
    fun onQueryClick(query: Query)
    fun onSearchItemClick(searchItem: SearchItem)
}