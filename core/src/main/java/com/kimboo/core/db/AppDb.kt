package com.kimboo.core.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.kimboo.core.model.ImgurGalleryPost

/**
 * Created by Agustin Tomas Larghi on 28/5/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
@Database(
        entities = [(ImgurGalleryPost::class)],
        version = 2,
        exportSchema = false
)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDb : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory : Boolean): AppDb {
            val databaseBuilder = if(useInMemory) {
                Room.inMemoryDatabaseBuilder(context, AppDb::class.java)
            } else {
                Room.databaseBuilder(context, AppDb::class.java, "meme_machine.db")
            }
            return databaseBuilder
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }

    abstract fun gallery(): ImgurGalleryDAO

}