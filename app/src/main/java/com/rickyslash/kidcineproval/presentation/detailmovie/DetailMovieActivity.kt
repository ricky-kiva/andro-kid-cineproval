package com.rickyslash.kidcineproval.presentation.detailmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rickyslash.kidcineproval.R

class DetailMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}