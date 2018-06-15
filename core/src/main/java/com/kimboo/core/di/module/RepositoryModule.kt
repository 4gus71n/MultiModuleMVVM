package com.kimboo.core.di.module

import com.kimboo.core.db.ImgurGalleryDAO
import com.kimboo.core.repository.ImgurGalleryRepository
import com.kimboo.core.retrofit.api.ImgurGalleryAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideImgurRepository(imgurGalleryAPI: ImgurGalleryAPI, imgurGalleryDAO: ImgurGalleryDAO) : ImgurGalleryRepository {
        return ImgurGalleryRepository(imgurGalleryAPI, imgurGalleryDAO)
    }

}

