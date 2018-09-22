package com.kimboo.core.utils

import android.content.Context
import android.content.Intent
import com.kimboo.core.model.ImgurGalleryPost

class MyAppIntents {

    companion object {
        const val ARG_IMGUR_GALLERY_POST = "arg_imgur_gallery_post"

        fun getPostDetailIntent(context: Context, imgurGalleryPost: ImgurGalleryPost): Intent {
            val intent = Intent(context, Class.forName("com.kimboo.postdetail.ui.PostDetailActivity"))
            intent.putExtra(ARG_IMGUR_GALLERY_POST, imgurGalleryPost)
            return intent
        }
    }

}