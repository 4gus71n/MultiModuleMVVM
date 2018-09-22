package com.kimboo.postdetail.ui

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.kimboo.core.model.ImgurGalleryPost
import com.kimboo.core.repository.ImgurGalleryRepository
import javax.inject.Inject

class PostDetailViewModel @Inject constructor(var imgurGalleryRepository: ImgurGalleryRepository): ViewModel() {

    val imgurGalleryPost = ObservableField<ImgurGalleryPost>()


}
