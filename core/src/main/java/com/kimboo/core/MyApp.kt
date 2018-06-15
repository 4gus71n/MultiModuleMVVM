package com.kimboo.core

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.kimboo.core.db.AppDb
import com.kimboo.core.di.component.AppComponent
import com.kimboo.core.di.component.DaggerAppComponent
import com.kimboo.core.di.module.AppModule
import com.kimboo.core.di.module.RoomModule

/**
 * Created by Agustin Tomas Larghi on 5/3/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class MyApp: Application() {

    private lateinit var appComponent: AppComponent

    fun provideBaseSubComponent() = appComponent.baseSubComponentBuilder().build()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        instance = this

        AppDb.create(this, false)

        appComponent = DaggerAppComponent.builder()
                .roomModule(RoomModule())
                .appModule(AppModule(this))
                .build()

        super.onCreate()
        Stetho.initializeWithDefaults(this);
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }

}