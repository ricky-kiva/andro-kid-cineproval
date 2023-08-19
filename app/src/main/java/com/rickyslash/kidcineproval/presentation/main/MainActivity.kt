package com.rickyslash.kidcineproval.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.rickyslash.kidcineproval.R
import com.rickyslash.kidcineproval.core.data.Resource
import com.rickyslash.kidcineproval.core.ui.MovieAdapter
import com.rickyslash.kidcineproval.databinding.ActivityMainBinding
import com.rickyslash.kidcineproval.presentation.detailmovie.DetailMovieActivity
import com.rickyslash.kidcineproval.presentation.favorite.FavoriteActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupActionBar()

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
                        binding.ibMovieRandom.setOnClickListener {
                            val intent = Intent(this@MainActivity, DetailMovieActivity::class.java)
                                .putExtra(DetailMovieActivity.EXTRA_DATA, movies.data?.randomOrNull())
                            startActivity(intent)
                        }
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

    private fun setupActionBar() {
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

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        if (menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                Toast.makeText(this@MainActivity, getString(R.string.dev_easter_web), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_opt_favorite -> {
                startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
                true
            }
            else -> true
        }
    }
}