package com.kimboo.core.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation

@Entity
data class ImgurGalleryPost(
    @PrimaryKey
    var id: String,
    var title: String? = null,
    var description: String? = null,
    var datetime: Int? = null,
    var cover: String? = null,
    var coverWidth: Int? = null,
    var coverHeight: Int? = null,
    var accountUrl: String? = null,
    var accountId: Int? = null,
    var privacy: String? = null,
    var layout: String? = null,
    var views: Int? = null,
    var link: String? = null,
    var ups: Int? = null,
    var downs: Int? = null,
    var points: Int? = null,
    var score: Int? = null,
    var isAlbum: Boolean? = null,
    var vote: Boolean? = null,
    var favorite: Boolean? = null,
    var nsfw: Boolean? = null,
    var section: String? = null,
    var commentCount: Int? = null,
    var favoriteCount: Int? = null,
    var topic: String? = null,
    var topicId: Int? = null,
    var imagesCount: Int? = null,
    var inGallery: Boolean? = null,
    var isAd: Boolean? = null,
    @Relation(parentColumn = "id", entityColumn = "name", entity = ImgurGalleryTag::class)
    var tags: List<ImgurGalleryTag>? = null,
    var inMostViral: Boolean? = null,
    @Relation(parentColumn = "id", entityColumn = "id", entity = ImgurGalleryImage::class)
    var images: List<ImgurGalleryImage>? = null
)

@Entity(foreignKeys = (arrayOf(ForeignKey(entity = ImgurGalleryPost::class,
        parentColumns = arrayOf("id"), childColumns = arrayOf("id"),
        onDelete = CASCADE, onUpdate = CASCADE))))
data class ImgurGalleryImage (
    @PrimaryKey
    var id: String,
    var title: String? = null,
    var description: String? = null,
    var datetime: Int? = null,
    var type: String? = null,
    var animated: Boolean? = null,
    var width: Int? = null,
    var height: Int? = null,
    var size: Int? = null,
    var views: Int? = null,
    var bandwidth: Int? = null,
    var vote: Boolean? = null,
    var favorite: Boolean? = null,
    var nsfw: Boolean? = null,
    var section: String? = null,
    var accountUrl: String? = null,
    var accountId: Int? = null,
    var isAd: Boolean? = null,
    var inMostViral: Boolean? = null,
    var hasSound: Boolean? = null,
    @Relation(parentColumn = "id", entityColumn = "name", entity = ImgurGalleryTag::class)
    var tags: List<ImgurGalleryTag>? = null,
    var adType: Int? = null,
    var adUrl: String? = null,
    var inGallery: Boolean? = null,
    var link: String? = null,
    var mp4: String? = null,
    var gifv: String? = null,
    var mp4Size: Int? = null,
    var looping: Boolean? = null,
    var commentCount: Int? = null,
    var favoriteCount: Int? = null,
    var ups: Int? = null,
    var downs: Int? = null,
    var points: Int? = null,
    var score: Int? = null
)

@Entity(foreignKeys = (arrayOf(
        ForeignKey(entity = ImgurGalleryPost::class, parentColumns = arrayOf("id"), childColumns = arrayOf("name")),
        ForeignKey(entity = ImgurGalleryImage::class, parentColumns = arrayOf("id"), childColumns = arrayOf("name"))
)))
data class ImgurGalleryTag (
    @PrimaryKey
    var name: String,
    var displayName: String? = null,
    var followers: Int? = null,
    var totalItems: Int? = null,
    var following: Boolean? = null,
    var backgroundHash: String? = null,
    var accent: String? = null,
    var backgroundIsAnimated: Boolean? = null,
    var thumbnailIsAnimated: Boolean? = null,
    var isPromoted: Boolean? = null,
    var description: String? = null
)