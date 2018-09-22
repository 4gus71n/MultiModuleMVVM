package com.kimboo.core.model

import android.app.ActionBar
import android.arch.persistence.room.*
import android.os.Parcelable
import android.support.annotation.Dimension
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import kotlinx.android.parcel.Parcelize

@Entity @Parcelize
data class ImgurGalleryPost (
        @PrimaryKey
        var id: String,
        var title: String?,
        var description: String?,
        var datetime: Int?,
        var cover: String?,
        var coverWidth: Int?,
        var coverHeight: Int?,
        var accountUrl: String?,
        var accountId: Int?,
        var privacy: String?,
        var layout: String?,
        var views: Int?,
        var link: String?,
        var ups: Int?,
        var downs: Int?,
        var points: Int?,
        var score: Int?,
        var isAlbum: Boolean?,
        var vote: Boolean?,
        var favorite: Boolean?,
        var nsfw: Boolean?,
        var section: String?,
        var commentCount: Int?,
        var favoriteCount: Int?,
        var topic: String?,
        var topicId: Int?,
        var imagesCount: Int?,
        var inGallery: Boolean?,
        var isAd: Boolean?,
        var inMostViral: Boolean?
): Parcelable  {
    //TODO Room @Ignore field doesn't work if it's declared as a constructor argument
    @Ignore
    var images: List<ImgurGalleryImage>? = null

    @Ignore
    var tags: List<ImgurGalleryTag>? = null

    fun getGalleryCoverImageUrl():String {
        if (cover.isNullOrBlank()) {
            return link ?: ""
        } else {
            val coverImage = images?.find { imgurGalleryImage -> imgurGalleryImage.link == cover }
            return coverImage?.let { link } ?: "https://i.imgur.com/$cover.jpg"
        }
    }

}

@Entity
data class ImgurGalleryImage (
    @PrimaryKey
    var id: String,
    var title: String?,
    var description: String?,
    var datetime: Int?,
    var type: String?,
    var animated: Boolean?,
    var width: Int?,
    var height: Int?,
    var size: Int?,
    var views: Int?,
    var vote: Boolean?,
    var favorite: Boolean?,
    var nsfw: Boolean?,
    var section: String?,
    var accountUrl: String?,
    var accountId: Int?,
    var isAd: Boolean?,
    var inMostViral: Boolean?,
    var hasSound: Boolean?,
    var adType: Int?,
    var adUrl: String?,
    var inGallery: Boolean?,
    var link: String?,
    var mp4: String?,
    var gifv: String?,
    var mp4Size: Int?,
    var looping: Boolean?,
    var commentCount: Int?,
    var favoriteCount: Int?,
    var ups: Int?,
    var downs: Int?,
    var points: Int?,
    var score: Int?
) {
    @Ignore
    var tags: List<ImgurGalleryTag>? = null

    var galleryId: String? = null
}

class FullImgurGalleryPost {
    @Embedded
    var imgurGalleryPost: ImgurGalleryPost? = null

    @Relation(parentColumn = "id", entityColumn = "galleryId", entity = ImgurGalleryTag::class)
    var tags: List<ImgurGalleryTag>? = null

    @Relation(parentColumn = "id", entityColumn = "galleryId", entity = ImgurGalleryImage::class)
    var images: List<ImgurGalleryImage>? = null
}

@Entity
data class ImgurGalleryTag(
    @PrimaryKey
    var name: String,
    var displayName: String?,
    var followers: Int?,
    var totalItems: Int?,
    var following: Boolean?,
    var backgroundHash: String?,
    var accent: String?,
    var backgroundIsAnimated: Boolean?,
    var thumbnailIsAnimated: Boolean?,
    var isPromoted: Boolean?,
    var description: String?
) {
    var galleryId: String? = null
    var galleryImageId: String? = null
}