package com.rickyslash.kidcineproval.core.utils

import com.rickyslash.kidcineproval.core.data.source.local.entity.MovieEntity
import com.rickyslash.kidcineproval.core.data.source.remote.response.MovieResponse
import com.rickyslash.kidcineproval.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                date = it.releaseDate,
                genre = it.genreIds[0],
                backdrop = it.backdropPath,
                voteAverage = it.voteAverage,
                overview = it.overview,
                favorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                date = it.date,
                genre = it.genre,
                backdrop = it.backdrop,
                voteAverage = it.voteAverage,
                overview = it.overview,
                favorite = it.favorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        date = input.date,
        genre = input.genre,
        backdrop = input.backdrop,
        voteAverage = input.voteAverage,
        overview = input.overview,
        favorite = input.favorite
    )

}