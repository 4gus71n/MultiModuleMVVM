package com.kimboo.core.retrofit.mapping

import com.kimboo.core.model.ImgurGalleryImage
import com.kimboo.core.model.ImgurGalleryPost
import com.kimboo.core.model.ImgurGalleryTag
import com.kimboo.core.retrofit.responses.gallery.ApiImgurGalleryImageResponse
import com.kimboo.core.retrofit.responses.gallery.ApiImgurGalleryResponse
import com.kimboo.core.retrofit.responses.gallery.ApiImgurGalleryTagResponse
import java.util.*

/**
 * Created by Agustin Tomas Larghi on 14/6/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */

fun mapApiImgurGalleryResponse(apiResponse: ApiImgurGalleryResponse?): List<ImgurGalleryPost> {
    return apiResponse?.data?.map { apiImgurGalleryPostResponse ->
        with(apiImgurGalleryPostResponse) {
            val imgurGalleryPost = ImgurGalleryPost(id, title, description, datetime, cover, coverWidth, coverHeight, accountUrl,
                    accountId, privacy, layout, views, link, ups, downs, points, score, isAlbum, vote, favorite,
                    nsfw, section, commentCount, favoriteCount, topic, topicId, imagesCount, inGallery, isAd,
                    inMostViral)
            imgurGalleryPost.tags = mapApiImgurGalleryTagsResponse(tags)
            imgurGalleryPost.images = mapApiImgurGalleryImagesResponse(images)
            imgurGalleryPost
        }
    } ?: ArrayList()
}

fun mapApiImgurGalleryImagesResponse(images: List<ApiImgurGalleryImageResponse>?): List<ImgurGalleryImage> {
    return images?.map { apiImgurGalleryImageResponse ->
        with (apiImgurGalleryImageResponse) {
            val imgurGalleryImage = ImgurGalleryImage(id, title, description, datetime, type, animated, width, height,
                    mp4Size, views, vote, favorite, nsfw, section, accountUrl, accountId, isAd,
                    inMostViral, hasSound, adType, adUrl, inGallery,
                    link, mp4, gifv, mp4Size, looping, commentCount, favoriteCount, ups, downs, points, score)
            imgurGalleryImage.tags = mapApiImgurGalleryTagsResponse(tags)
            imgurGalleryImage
        }
    } ?: ArrayList()
}

fun mapApiImgurGalleryTagsResponse(tags: List<ApiImgurGalleryTagResponse>?): List<ImgurGalleryTag> {
    return tags?.map { apiImgurGalleryTagResponse ->
        with (apiImgurGalleryTagResponse) {
            ImgurGalleryTag(name, displayName, followers, totalItems, following, backgroundHash,
                    accent, backgroundIsAnimated, thumbnailIsAnimated, isPromoted, description)
        }
    } ?: ArrayList()
}