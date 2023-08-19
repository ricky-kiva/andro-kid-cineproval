package com.rickyslash.kidcineproval.di

import com.rickyslash.kidcineproval.core.domain.usecase.MovieInteractor
import com.rickyslash.kidcineproval.core.domain.usecase.MovieUseCase
import com.rickyslash.kidcineproval.presentation.detailmovie.DetailMovieViewModel
import com.rickyslash.kidcineproval.presentation.favorite.FavoriteViewModel
import com.rickyslash.kidcineproval.presentation.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}