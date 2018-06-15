package com.kimboo.core.retrofit.api

import com.kimboo.core.retrofit.responses.gallery.ApiImgurGalleryResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Agustin Tomas Larghi on 12/6/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
interface ImgurGalleryAPI {

    //region Unsorted API endpoints
    @GET("gallery/search/{sort}/{window}/{page}")
    fun gallerySearch(@Path("sort") sort: String = "top",
                      @Path("window") window: String = "all",
                      @Path("page") page: Int = 0,
                      @Query("q") query: String) : Observable<Response<ApiImgurGalleryResponse>>
    //endregion

}