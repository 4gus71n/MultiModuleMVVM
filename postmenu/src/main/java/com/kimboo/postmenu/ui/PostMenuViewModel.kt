package com.kimboo.postmenu.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kimboo.core.repository.ImgurRepository

/**
 * Created by Agustin Tomas Larghi on 12/6/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class PostMenuViewModel constructor(val imgurRepository: ImgurRepository) : ViewModel() {

    var uiEvents = MutableLiveData<String>()

    public class ViewModelProvider(val imgurRepository: ImgurRepository): android.arch.lifecycle.ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PostMenuViewModel(imgurRepository) as T
        }
    }

    fun onPostButtonClicked() {
        uiEvents.postValue(imgurRepository.test())
    }
}