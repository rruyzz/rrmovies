package com.app.features.home.watchList.data

import android.util.Log
import com.app.commons.models.Movie
import com.app.features.home.watchList.domain.repository.WatchListRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class WatchListRepositoryImpl() : WatchListRepository {
    override suspend fun getMovies(): Flow<List<Movie>> {
        try {
            Firebase.firestore
                .collection("users")
                .document(Firebase.auth.currentUser?.uid.orEmpty())
                .collection("movieId")
                .get()
                .await()
                .also {
                    return if(it.isEmpty.not()){
                        val list = mutableListOf<Movie>()
                        it.documents.forEach { doc ->
                            list.add(doc.mapperMovie())
                        }
                        flow {
                            emit(list)
                        }
                    } else {
                        flow {
                            emit(listOf())
                        }
                    }
                }
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun DocumentSnapshot.mapperMovie(): Movie {
        val poster: String = this["poster"].toString()
        val posterBack: String = this["posterBack"].toString()
        val title: String = this["title"].toString()
        val description: String = this["description"].toString()
        val year: String = this["year"].toString()
        val time: String = this["time"].toString()
        val gender: String = this["gender"].toString()
        val grade: String = this["grade"].toString()
        val id: Int = this["id"].toString().toInt()
        return Movie(poster,posterBack, title, description, year, time, gender, grade, id)
    }
}