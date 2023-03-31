package com.app.commons.data
import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.commons.domain.dao.MovieDao
import com.app.commons.models.Movie


@Database(
    entities = [Movie::class],
    version = 1
)
abstract class MovieRoomDatabase: RoomDatabase() {

    abstract val dao: MovieDao

}