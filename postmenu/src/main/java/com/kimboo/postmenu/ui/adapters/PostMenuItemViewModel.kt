package com.kimboo.postmenu.ui.adapters

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.view.ViewGroup
import android.widget.ImageView
import com.kimboo.core.di.module.GlideApp
import com.kimboo.core.model.ImgurGalleryPost


class PostMenuItemViewModel(val callbackEvents: MutableLiveData<Pair<PostMenuAdapter.PostMenuAdapterEvent, ImgurGalleryPost>>): ViewModel() {

    constructor (callbackEvents: MutableLiveData<Pair<PostMenuAdapter.PostMenuAdapterEvent, ImgurGalleryPost>>,
                 imgurPost: ImgurGalleryPost): this(callbackEvents) {
        imgurGalleryPost.set(imgurPost)
    }

    val imgurGalleryPost = ObservableField<ImgurGalleryPost>()

    fun onImgurGalleryClicked() {
        imgurGalleryPost.get()?.let { imgurGalleryPost ->
            callbackEvents.postValue(Pair(PostMenuAdapter.PostMenuAdapterEvent.GALLERY_CLICK, imgurGalleryPost))
        }
    }

    companion object {
        @JvmStatic @BindingAdapter(value = "app:coverImage", requireAll = true)
        fun bindImageHeight(imageView: ImageView, imgurGalleryPost: ImgurGalleryPost?) {
            imgurGalleryPost?.let {
                val layoutParams = imageView.getLayoutParams()
                layoutParams.height = it.coverHeight ?: ViewGroup.LayoutParams.WRAP_CONTENT
                imageView.setLayoutParams(layoutParams)

                val glideBuilder = GlideApp.with(imageView.context)
                        .load(it.getGalleryCoverImageUrl())

                glideBuilder.into(imageView)
            }
        }
    }

}