package com.kimboo.postmenu.ui.adapters

import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.databinding.InverseBindingMethod
import android.databinding.InverseMethod
import android.databinding.ObservableField
import android.support.constraint.Guideline
import android.view.View
import android.widget.ImageView
import com.kimboo.core.di.module.GlideApp
import com.kimboo.core.model.ImgurGalleryPost
import android.support.constraint.ConstraintLayout



class PostMenuItemViewModel(): ViewModel() {

    constructor (imgurPost: ImgurGalleryPost): this() {
        imgurGalleryPost.set(imgurPost)
    }

    val imgurGalleryPost = ObservableField<ImgurGalleryPost>()

    fun getAspectRatio(imgurGalleryPost: ImgurGalleryPost?): Float {
        return imgurGalleryPost?.let {
            it.coverHeight?.div(it.coverWidth?.toFloat() ?: 1f)
        } ?: 100f
    }

    companion object {
        //TODO https://stackoverflow.com/questions/44845121/constraintlayout-with-databinding
        @JvmStatic @BindingAdapter(value = "app:layout_constraintGuide_percent", requireAll = true)
        fun bindConstraintLayout(guideline: Guideline, float: Float?) {
            float?.let {
                val params = guideline.layoutParams as ConstraintLayout.LayoutParams
                params.guidePercent = it
                guideline.layoutParams = params
            }
        }

        @JvmStatic @BindingAdapter(value = "app:cover_image", requireAll = true)
        fun bindImageUrl(imageView: ImageView, imgurGalleryPost: ImgurGalleryPost?) {
            imgurGalleryPost?.let { imgurPost ->
                val glideBuilder = GlideApp.with(imageView.context)
                        .load(imgurPost.getGalleryCoverImageUrl())
                glideBuilder.into(imageView)
            }
        }

        @JvmStatic @BindingAdapter(value = "app:cover_height", requireAll = true)
        fun bindImageHeight(view: ImageView, height: Int?) {
            height?.let {
                val layoutParams = view.getLayoutParams()
                layoutParams.height = it
                view.setLayoutParams(layoutParams)
            }
        }

        @JvmStatic @BindingAdapter(value = "app:cover_width", requireAll = true)
        fun bindImageWidth(view: ImageView, width: Int?) {
            width?.let {
                val layoutParams = view.getLayoutParams()
                layoutParams.width = it
                view.setLayoutParams(layoutParams)
            }
        }
    }

}