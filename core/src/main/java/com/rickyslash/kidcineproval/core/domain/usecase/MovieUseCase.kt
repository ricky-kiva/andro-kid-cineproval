package com.rickyslash.kidcineproval.core.domain.usecase

import com.rickyslash.kidcineproval.core.data.Resource
import com.rickyslash.kidcineproval.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

}