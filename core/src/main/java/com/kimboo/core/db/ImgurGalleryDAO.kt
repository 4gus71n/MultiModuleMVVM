package com.kimboo.core.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.kimboo.core.model.ImgurGalleryPost
import io.reactivex.Flowable

/**
 * Created by Agustin Tomas Larghi on 12/6/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
@Dao()
interface ImgurGalleryDAO {

    @Query("SELECT * from imgurgallerypost where title like :query")
    fun gallerySearch(query: String): Flowable<List<ImgurGalleryPost>>

    @Insert
    fun insertGallerySearch(imgurGalleryPosts: List<ImgurGalleryPost>?)
}