package com.app.commons.room

import androidx.room.*
import com.app.commons.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertMovie(movie: Movie)

    @Delete
    suspend fun deleteContact(movie: Movie)

    @Query("SELECT * FROM movie ORDER BY title ASC")
    fun getMovies() : Flow<List<Movie>>

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id)")
    fun hasSaved(id : Int): Flow<Boolean>

}