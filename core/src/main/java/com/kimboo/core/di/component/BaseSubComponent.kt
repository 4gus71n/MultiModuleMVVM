package com.kimboo.core.di.component

import android.content.Context
import com.kimboo.core.repository.ImgurRepository
import dagger.Subcomponent

/**
 * Created by Agustin Tomas Larghi on 12/6/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
@Subcomponent
interface BaseSubComponent {

    val imgurRepository: ImgurRepository

    val context: Context

    @Subcomponent.Builder
    interface Builder {
        fun build(): BaseSubComponent
    }
}
