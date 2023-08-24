package com.rickyslash.kidcineproval.core.domain.usecase

import com.rickyslash.kidcineproval.core.data.Resource
import com.rickyslash.kidcineproval.core.domain.model.Movie
import com.rickyslash.kidcineproval.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieUseCaseTest {

    @Mock
    private lateinit var movieRepository: IMovieRepository

    private lateinit var movieUseCase: MovieUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        movieUseCase = MovieInteractor(movieRepository)
    }

    @Test
    fun `test getAllMovie returns list of movies`() = runBlocking {
        val movies = listOf(
            Movie(1, "Movie 1", "2023-08-24", 1, "backdrop1.jpg", 7.5, "overview of Movie 1", "poster1.jpg", true),
            Movie(2, "Movie 2", "2023-08-25", 2, "backdrop2.jpg", 8.0, "overview of Movie 2", "poster2.jpg", false),
            Movie(3, "Movie 3", "2023-08-26", 3, "backdrop3.jpg", 6.8, "overview of Movie 3", "poster3.jpg", true)
        )

        `when`(movieRepository.getAllMovie()).thenReturn(flow {
            emit(Resource.Success(movies))
        })

        val result = movieUseCase.getAllMovie()

        result.collect { resource ->
            assertTrue(resource is Resource.Success)

            val movieResource = resource as Resource.Success
            assertEquals(movies, movieResource.data)
        }

        verify(movieRepository, times(1)).getAllMovie()
        verifyNoMoreInteractions(movieRepository)
    }
}