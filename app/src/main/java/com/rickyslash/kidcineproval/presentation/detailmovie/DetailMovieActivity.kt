package com.rickyslash.kidcineproval.presentation.detailmovie

import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.rickyslash.kidcineproval.R
import com.rickyslash.kidcineproval.core.domain.model.Movie
import com.rickyslash.kidcineproval.core.utils.approvalComment
import com.rickyslash.kidcineproval.core.utils.dateKebabToSentence
import com.rickyslash.kidcineproval.databinding.ActivityDetailMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDetailMovieBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupActionBar()

        val movieData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA, Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }
        setupView(movieData)
    }

    private fun setupView(data: Movie?) {
        
        val compBaseColor = with(TypedValue()) {
            theme.resolveAttribute(com.google.android.material.R.attr.colorPrimaryVariant, this, true)
            ContextCompat.getColor(this@DetailMovieActivity, resourceId)
        }
        
        data?.let {
            binding.tvMovieTitle.text = it.title
            binding.tvMovieDate.text = dateKebabToSentence(it.date)
            binding.tvMovieOverview.text = it.overview

            var isFavorite = it.favorite
            setStatusApproved(isFavorite, it.voteAverage, compBaseColor)
            binding.btnApproves.setOnClickListener {
                isFavorite = !isFavorite
                detailMovieViewModel.setFavoriteMovie(data, isFavorite)
                setStatusApproved(isFavorite, data.voteAverage, compBaseColor)
                if (isFavorite) {
                    Toast.makeText(this, approvalComment(this, data.voteAverage), Toast.LENGTH_SHORT).show()
                }
            }

            binding.ibApproved.setOnClickListener {
                if (isFavorite) {
                    Toast.makeText(this, approvalComment(this, data.voteAverage), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, getString(R.string.label_approve_decide_first), Toast.LENGTH_SHORT).show()
                }
            }

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${it.poster}")
                .placeholder(ColorDrawable(compBaseColor))
                .into(binding.ivContainer)
        }
    }

    private fun setStatusApproved(status: Boolean, voteAverage: Double, baseColor: Int) {
        if (status) {
            when {
                voteAverage >= 8.0 -> binding.mcvApproved.setCardBackgroundColor(ContextCompat.getColor(this, R.color.GREEN))
                voteAverage in 7.0..8.0 -> binding.mcvApproved.setCardBackgroundColor(ContextCompat.getColor(this, R.color.YELLOW_ALT))
                voteAverage in 6.0..7.0 -> binding.mcvApproved.setCardBackgroundColor(ContextCompat.getColor(this, R.color.RED_LV2))
                voteAverage < 6.0 -> binding.mcvApproved.setCardBackgroundColor(ContextCompat.getColor(this, R.color.RED_LV1))
                else -> binding.mcvApproved.setCardBackgroundColor(baseColor)
            }
        } else {
            binding.mcvApproved.setCardBackgroundColor(baseColor)
        }
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            val colorPrimary = with(TypedValue()) {
                theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, this, true)
                ContextCompat.getColor(this@DetailMovieActivity, resourceId)
            }

            elevation = 0f
            setBackgroundDrawable(ColorDrawable(colorPrimary))
            title = getString(R.string.label_approve_or_not)
            setDisplayHomeAsUpEnabled(true)
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

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}