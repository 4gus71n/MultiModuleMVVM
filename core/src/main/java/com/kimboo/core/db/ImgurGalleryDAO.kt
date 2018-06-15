package com.kimboo.core.db

import android.arch.persistence.room.*
import com.kimboo.core.model.FullImgurGalleryPost
import com.kimboo.core.model.ImgurGalleryImage
import com.kimboo.core.model.ImgurGalleryPost
import com.kimboo.core.model.ImgurGalleryTag
import io.reactivex.Flowable

/**
 * Created by Agustin Tomas Larghi on 12/6/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
@Dao
abstract class ImgurGalleryDAO {

    fun gallerySearch(query: String): Flowable<List<ImgurGalleryPost>> {
        return getFullImgurGalleryPost(query).map{ fullImgurGalleryPosts ->
            fullImgurGalleryPosts.mapNotNull { fullImgurGalleryPost ->
                val imgurGalleryPost = fullImgurGalleryPost.imgurGalleryPost!!
                imgurGalleryPost.images = fullImgurGalleryPost.images
                imgurGalleryPost.tags = fullImgurGalleryPost.tags
                imgurGalleryPost
            }
        }
    }

    @Transaction @Query("SELECT * from ImgurGalleryPost where title like :query")
    abstract fun getFullImgurGalleryPost(query: String): Flowable<List<FullImgurGalleryPost>>

    @Query("SELECT * from ImgurGalleryPost where title like :query")
    abstract fun getImgurGalleryPost(query: String): Flowable<List<ImgurGalleryPost>>

    @Query("SELECT * from imgurgallerytag where galleryId = :id")
    abstract fun getImgurGalleryTags(id: String): Flowable<List<ImgurGalleryTag>>

    @Query("SELECT * from imgurgalleryimage where galleryId = :id")
    abstract fun getImgurGalleryImages(id: String): Flowable<List<ImgurGalleryImage>>

    //@Transaction @Query("SELECT * from imgurgallerypost where title like :query")
    //abstract fun getFullImgurGalleryPost(query: String): Flowable<List<FullImgurGalleryPost>>

    fun insertGallerySearch(imgurGalleryPosts: List<ImgurGalleryPost>?) {
        imgurGalleryPosts?.forEach { imgurGalleryPost ->
            //Insert the tags of the images
            imgurGalleryPost.images?.forEach { imgurImage ->
                imgurImage.tags?.forEach { imgurImageTag ->
                    imgurImageTag.galleryImageId = imgurImage.id
                    imgurImageTag.galleryId = imgurGalleryPost.id
                }
                insertTags(imgurImage.tags)
            }
            //Insert the images
            imgurGalleryPost.images?.forEach { imgurImage ->
                imgurImage.galleryId = imgurGalleryPost.id
            }
            insertImages(imgurGalleryPost.images)
        }
        //Insert the gallery posts
        insertGalleryPost(imgurGalleryPosts)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertImages(images: List<ImgurGalleryImage>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTags(tags: List<ImgurGalleryTag>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun insertGalleryPost(imgurGalleryPosts: List<ImgurGalleryPost>?)

}