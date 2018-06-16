package com.kimboo.core.di.module

import android.content.Context
import com.kimboo.core.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: MyApp) {
    @Provides
    @Singleton
    internal fun provideApp(): MyApp = app

    @Provides
    @Singleton
    internal fun provideContext(): Context = app.baseContext

}
