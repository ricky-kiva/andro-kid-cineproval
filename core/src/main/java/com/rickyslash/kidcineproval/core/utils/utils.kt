package com.rickyslash.kidcineproval.core.utils

import com.rickyslash.kidcineproval.core.R
import java.text.SimpleDateFormat
import java.util.Locale

fun dateKebabToSentence(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val outputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.US)

    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date!!)
}

fun approvalComment(voteAverage: Double): Int {
    return when {
        voteAverage >= 8.0 -> R.string.label_approved_80_up
        voteAverage in 7.0..8.0 -> R.string.label_approved_70_80
        voteAverage in 6.0..7.0 -> R.string.label_approved_60_70
        voteAverage < 6.0 -> R.string.label_approved_below_60
        else ->R.string.label_approve_decide_first
    }
}

fun cultureScore(averages: Double): Int {
    return when {
        averages >= 8.0 -> R.string.label_culture_80_up
        averages in 7.0..8.0 -> R.string.label_culture_70_80
        averages in 6.0..7.0 -> R.string.label_culture_60_70
        averages < 6.0 -> R.string.label_culture_below_60
        else -> R.string.err_operation_empty
    }
}