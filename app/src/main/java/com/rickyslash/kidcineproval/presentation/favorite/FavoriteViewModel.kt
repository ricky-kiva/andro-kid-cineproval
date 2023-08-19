package com.rickyslash.kidcineproval.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rickyslash.kidcineproval.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase): ViewModel() {

    val favoriteMovie = movieUseCase.getFavoriteMovies().asLiveData()

}