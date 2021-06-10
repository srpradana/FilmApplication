package com.example.filmapplication.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    var id: Int? = null,
    var posterPath: String? = null,
    var title: String? = null,
    var date: String? = null,
    var overview: String? = null,
    var vote: Double? = null,
    var favCheck: Boolean = false
): Parcelable
