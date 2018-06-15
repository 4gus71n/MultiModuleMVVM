package com.kimboo.core.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import com.kimboo.core.db.ImgurGalleryDAO
import com.kimboo.core.model.ImgurGalleryPost
import com.kimboo.core.retrofit.api.ImgurGalleryAPI
import com.kimboo.core.retrofit.mapping.mapApiImgurGalleryResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * I've a small naming convention here:
 * - I use the fetch prefix for a function that does a server-side call and returns the result.
 * - I use the get prefix for a function that does a DB query and returns the result.
 * - I use the retrive prefix for a function that does a server-side call and returns the database
 * Flowable to observe the data.
 */
class ImgurGalleryRepository(val imgurGalleryAPI: ImgurGalleryAPI, val imgurGalleryDAO: ImgurGalleryDAO) {

    val networkStatus =  MutableLiveData<String>()

    fun retrieveSearchGallery(sort: String = "top", window: String = "all", page: Int = 0, query: String):
        LiveData<List<ImgurGalleryPost>> {
        fetchGallerySearch(sort, window, page, query)
            //TODO Extract into Extended Function
            .subscribe({imgurGalleryPosts ->
                imgurGalleryDAO.insertGallerySearch(imgurGalleryPosts)
            }, {imgurGalleryPostError ->
                networkStatus.postValue("Something went wrong with RxJava ${imgurGalleryPostError.toString()}: ${imgurGalleryPostError.localizedMessage}")
            })

        return getGallerySearch(query)
    }

    fun getGallerySearch(query: String) = LiveDataReactiveStreams
            .fromPublisher(imgurGalleryDAO.gallerySearch("%$query%"))

    fun fetchGallerySearch(sort: String, window: String, page: Int, query: String): Observable<List<ImgurGalleryPost>> {
        return imgurGalleryAPI.gallerySearch(sort, window, page, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //TODO Extract into Extended Function
                .flatMap { response ->
                    if (response.isSuccessful) {
                        Observable.just(response.body())
                    } else {
                        networkStatus.postValue("Non-successfull response ${response.code()} : ${response.errorBody()}")
                        Observable.empty()
                    }
                }
                //TODO Extract into Extended Function
                .flatMap { response ->
                    Observable.just(mapApiImgurGalleryResponse(response))
                }
    }

}
