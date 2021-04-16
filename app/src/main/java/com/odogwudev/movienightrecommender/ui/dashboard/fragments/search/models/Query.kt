package com.odogwudev.movienightrecommender.ui.dashboard.fragments.search.models

import com.odogwudev.movienightrecommender.models.LocalModel

class Query(
    var queryName: String
) : LocalModel {
    constructor() : this("")
}