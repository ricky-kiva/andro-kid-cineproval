package com.rickyslash.kidcineproval.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    var id: Int,
    var title: String,
    var date: String,
    var genre: Int,
    var backdrop: String,
    var voteAverage: Double,
    var overview: String,
    var poster: String,
    var favorite: Boolean = false
): Parcelable