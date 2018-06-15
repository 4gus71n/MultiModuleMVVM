package com.kimboo.core.di.component

import android.content.Context
import com.kimboo.core.repository.ImgurGalleryRepository
import dagger.Subcomponent

/**
 * Created by Agustin Tomas Larghi on 12/6/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
@Subcomponent
interface BaseSubComponent {

    val imgurGalleryRepository: ImgurGalleryRepository

    val context: Context

    @Subcomponent.Builder
    interface Builder {
        fun build(): BaseSubComponent
    }
}
