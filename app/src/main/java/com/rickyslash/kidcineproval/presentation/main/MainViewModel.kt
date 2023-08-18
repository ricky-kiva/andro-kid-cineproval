package com.rickyslash.kidcineproval.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rickyslash.kidcineproval.core.domain.usecase.MovieUseCase

class MainViewModel(movieUseCase: MovieUseCase): ViewModel() {

    val movies = movieUseCase.getAllMovie().asLiveData()

}