package com.kimboo.postmenu.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kimboo.core.model.ImgurGalleryPost
import com.kimboo.core.repository.ImgurGalleryRepository

/**
 * Created by Agustin Tomas Larghi on 12/6/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class PostMenuViewModel constructor(val imgurGalleryRepository: ImgurGalleryRepository) : ViewModel() {

    lateinit var galleryPostList: LiveData<List<ImgurGalleryPost>>

    var uiEvents = MutableLiveData<String>()
    var networkEvents = imgurGalleryRepository.networkStatus

    public class ViewModelProvider(val imgurGalleryRepository: ImgurGalleryRepository): android.arch.lifecycle.ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PostMenuViewModel(imgurGalleryRepository) as T
        }
    }

    fun hookGallerySearch(): LiveData<List<ImgurGalleryPost>> {
        galleryPostList = imgurGalleryRepository.retrieveSearchGallery("top", "all", 1, "cats")
        return galleryPostList
    }

    fun onPostButtonClicked() {
    }
}