package com.kimboo.core.di.module

import com.kimboo.core.db.ImgurDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule() {

    //TODO PASS DB instance
    //TODO Add provides for the DB instance
    @Singleton
    @Provides
    fun providesImgurDAO() = ImgurDAO()

}
