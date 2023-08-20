package com.rickyslash.kidcineproval.favorite.di

import com.rickyslash.kidcineproval.favorite.presentation.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}