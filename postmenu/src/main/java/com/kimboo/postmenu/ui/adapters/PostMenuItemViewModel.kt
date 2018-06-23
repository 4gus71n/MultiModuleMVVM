package com.kimboo.postmenu.ui.adapters

import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kimboo.core.model.ImgurGalleryPost

class PostMenuItemViewModel(): ViewModel() {

    constructor (imgurPost: ImgurGalleryPost): this() {
        imgurGalleryPost.set(imgurPost)
    }

    val imgurGalleryPost = ObservableField<ImgurGalleryPost>()

    companion object {
        @JvmStatic @BindingAdapter(value = "app:imageUrl", requireAll = true)
        fun bindImageUrl(imageView: ImageView, url: String?) {
            url.let {
                Glide.with(imageView.context).load(it).into(imageView)
            }
        }
    }

}