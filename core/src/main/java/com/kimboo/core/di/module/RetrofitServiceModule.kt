package com.kimboo.core.di.module

import com.kimboo.core.retrofit.api.ImgurGalleryAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
/**
 * Created by Agustin Tomas Larghi on 9/12/2017.
 * Email: agustin.tomas.larghi@gmail.com
 */
@Module
class RetrofitServiceModule {

    @Provides
    fun provideImgurAPI(retrofit: Retrofit): ImgurGalleryAPI {
        return retrofit.create(ImgurGalleryAPI::class.java)
    }

}

