package com.kimboo.postmenu.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kimboo.core.model.ImgurGalleryPost
import com.kimboo.core.repository.ImgurGalleryRepository
import javax.inject.Inject

/**
 * Created by Agustin Tomas Larghi on 12/6/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class PostMenuViewModel @Inject constructor(var imgurGalleryRepository: ImgurGalleryRepository) : ViewModel() {

    val galleryPostList: LiveData<List<ImgurGalleryPost>> =
            imgurGalleryRepository.retrieveSearchGallery("top", "all", 1, "cats")

    var uiEvents = MutableLiveData<String>()
    var networkEvents = imgurGalleryRepository.networkStatus


    fun onPostButtonClicked() {
    }
}