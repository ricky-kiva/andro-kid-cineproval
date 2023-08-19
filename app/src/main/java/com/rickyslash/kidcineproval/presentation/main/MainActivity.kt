package com.rickyslash.kidcineproval.presentation.main

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.rickyslash.kidcineproval.R
import com.rickyslash.kidcineproval.core.data.Resource
import com.rickyslash.kidcineproval.core.ui.MovieAdapter
import com.rickyslash.kidcineproval.databinding.ActivityMainBinding
import com.rickyslash.kidcineproval.presentation.detailmovie.DetailMovieActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by inject()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()

        val movieAdapter = MovieAdapter()

        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@MainActivity, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        mainViewModel.movies.observe(this) { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        movieAdapter.setData(movies.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvErrListMovie.visibility = View.VISIBLE
                        binding.tvErrListMovie.text = movies.message ?: getString(R.string.err_get_movie_data)
                    }
                }
            }
        }

        with(binding.rvListMovie) {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }
    }

    private fun setupView() {
        supportActionBar?.apply {
            val colorOnPrimary = with(TypedValue()) {
                theme.resolveAttribute(com.google.android.material.R.attr.colorOnPrimary, this, true)
                ContextCompat.getColor(this@MainActivity, resourceId)
            }
            val colorPrimary = with(TypedValue()) {
                theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, this, true)
                ContextCompat.getColor(this@MainActivity, resourceId)
            }

            elevation = 0f
            val text = SpannableString(title)
            text.setSpan(ForegroundColorSpan(colorOnPrimary), 0, text.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            setBackgroundDrawable(ColorDrawable(colorPrimary))
            title = text
            setHomeAsUpIndicator(R.drawable.ic_comedy_24)
            setHomeActionContentDescription(getString(R.string.app_name))
            setDisplayHomeAsUpEnabled(true)
        }
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}