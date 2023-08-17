package com.rickyslash.kidcineproval.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun dateKebabToSentence(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val outputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.US)

    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date!!)
}

fun getGenreNameFromId(genreId: Int): String {
    return when (genreId) {
        28 -> "Action"
        12 -> "Adventure"
        16 -> "Animation"
        35 -> "Comedy"
        80 -> "Crime"
        99 -> "Documentary"
        18 -> "Drama"
        10751 -> "Family"
        14 -> "Fantasy"
        36 -> "History"
        27 -> "Horror"
        10402 -> "Music"
        9648 -> "Mystery"
        10749 -> "Romance"
        878 -> "Science Fiction"
        10770 -> "TV Movie"
        53 -> "Thriller"
        10752 -> "War"
        37 -> "Western"
        else -> "Unknown Genre"
    }
}