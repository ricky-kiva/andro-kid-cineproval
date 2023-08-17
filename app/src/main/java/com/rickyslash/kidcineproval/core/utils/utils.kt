package com.rickyslash.kidcineproval.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun dateKebabToSentence(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val outputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.US)

    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date)
}