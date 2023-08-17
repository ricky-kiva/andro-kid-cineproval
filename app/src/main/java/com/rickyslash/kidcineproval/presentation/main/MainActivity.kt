package com.rickyslash.kidcineproval.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rickyslash.kidcineproval.R
import com.rickyslash.kidcineproval.core.ui.MovieAdapter
import com.rickyslash.kidcineproval.databinding.ActivityMainBinding
import com.rickyslash.kidcineproval.presentation.detailmovie.DetailMovieActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieAdapter = MovieAdapter()

        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@MainActivity, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }



    }
}