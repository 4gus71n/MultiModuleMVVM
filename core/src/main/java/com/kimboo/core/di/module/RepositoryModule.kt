package com.kimboo.core.di.module

import com.kimboo.core.db.ImgurDAO
import com.kimboo.core.repository.ImgurRepository
import com.kimboo.core.retrofit.api.ImgurAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideImgurRepository(imgurAPI: ImgurAPI, imgurDAO: ImgurDAO) : ImgurRepository {
        return ImgurRepository(imgurAPI, imgurDAO)
    }

}

