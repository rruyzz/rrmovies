package com.app.detail.main.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.app.commons.models.Movie
import com.app.commons.room.MovieDao
import com.app.detail.main.domain.repository.DetailMovieRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class DetailMovieRepositoryImpl(
    private val dao: MovieDao
) : DetailMovieRepository {

    override suspend fun deleteMovie(movie: Movie): Flow<Boolean> {
        try {
            Firebase.firestore
                .collection("users")
                .document(Firebase.auth.currentUser?.uid.orEmpty())
                .collection("movieId")
                .document(movie.id.toString())
                .delete()
                .await()
            Log.d("FirestoreSave", "SUCESSO DELETE")
        } catch (exception: Exception) {
            Log.d("FirestoreSave", exception.message.toString())
            throw exception
        }
        return flow {
            emit(false)
        }
    }

    override suspend fun hasSaved(id: Int): Flow<Boolean> {
        try {
            Firebase.firestore.collection("users")
                .document(Firebase.auth.currentUser?.uid.orEmpty())
                .collection("movieId")
                .document(id.toString())
                .get()
                .await()
                .also { return flow { emit(it.exists()) } }
        } catch (exception: Exception) {
            return flow { emit(false) }
        }
    }

    override suspend fun upsertMovie(movie: Movie): Flow<Boolean> {
        try {
            Firebase.firestore.collection("users")
                .document(Firebase.auth.currentUser?.uid.orEmpty())
                .collection("movieId")
                .document(movie.id.toString())
                .set(movie)
                .await()
            Log.d("FirestoreSave", "SUCESSO SAVE")
        } catch (exception: Exception) {
            Log.d("FirestoreSave", exception.message.toString())
            throw exception
        }
        return flow {
            emit(true)
        }
    }
}