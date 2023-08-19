package com.rickyslash.kidcineproval.presentation.detailmovie

import androidx.lifecycle.ViewModel
import com.rickyslash.kidcineproval.core.domain.model.Movie
import com.rickyslash.kidcineproval.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)

}