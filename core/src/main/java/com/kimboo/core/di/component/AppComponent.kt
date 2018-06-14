package com.kimboo.core.di.component

import com.kimboo.core.MyApp
import com.kimboo.core.di.module.*
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Agustin Tomas Larghi on 12/6/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (RetrofitServiceModule::class), (RoomModule::class), (RepositoryModule::class)])
interface AppComponent {

    fun baseSubComponentBuilder(): BaseSubComponent.Builder

    val myApp: MyApp
}