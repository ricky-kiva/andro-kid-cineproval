package com.rickyslash.kidcineproval.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    var id: Int,
    var title: String,
    var date: String,
    var genre: Int,
    var backdrop: Int,
    var voteAverage: Double,
    var overview: String
): Parcelable