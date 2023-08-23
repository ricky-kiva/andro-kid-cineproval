package com.rickyslash.kidcineproval.favorite.presentation.favorite

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.rickyslash.kidcineproval.R
import com.rickyslash.kidcineproval.core.domain.model.Movie
import com.rickyslash.kidcineproval.core.ui.MovieAdapter
import com.rickyslash.kidcineproval.core.utils.cultureScore
import com.rickyslash.kidcineproval.favorite.databinding.ActivityFavoriteBinding
import com.rickyslash.kidcineproval.favorite.di.favoriteModule
import com.rickyslash.kidcineproval.presentation.detailmovie.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityFavoriteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadKoinModules(favoriteModule)

        setupActionBar()

        val movieAdapter = MovieAdapter()

        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@FavoriteActivity, DetailMovieActivity::class.java)
                .putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovie.observe(this) { movies ->
            movieAdapter.submitList(movies)
            binding.tvEmptyApproved.visibility = if (movies.isNotEmpty()) View.GONE else View.VISIBLE
            binding.ibEvalApproved.setOnClickListener { analyzeVoteAverage(movies) }
        }

        with(binding.rvListApproved) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            val colorOnPrimary = with(TypedValue()) {
                theme.resolveAttribute(com.google.android.material.R.attr.colorOnPrimary, this, true)
                ContextCompat.getColor(this@FavoriteActivity, resourceId)
            }
            val colorPrimary = with(TypedValue()) {
                theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, this, true)
                ContextCompat.getColor(this@FavoriteActivity, resourceId)
            }

            elevation = 0f
            val text = SpannableString(getString(R.string.label_approved))
            text.setSpan(ForegroundColorSpan(colorOnPrimary), 0, text.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            setBackgroundDrawable(ColorDrawable(colorPrimary))
            title = text
            setHomeAsUpIndicator(R.drawable.round_arrow_back_24)
            setHomeActionContentDescription(R.string.label_back)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun analyzeVoteAverage(movies: List<Movie>) {
        if (movies.isNotEmpty()) {
            val averages =  "%.2f".format(movies.map { it.voteAverage }.average()).toDouble()
            Toast.makeText(this, "Score of Excellence: $averages\n${getString(cultureScore(averages))}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.err_operation_empty), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> true
        }
    }
}