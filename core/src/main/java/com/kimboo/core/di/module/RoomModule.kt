package com.kimboo.core.di.module

import android.content.Context
import com.kimboo.core.db.AppDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule() {

    @Singleton
    @Provides
    fun providesAppDb(context: Context) = AppDb.create(context, false)

    @Singleton
    @Provides
    fun providesImgurGalleryDAO(appDb: AppDb) = appDb.gallery()

}
