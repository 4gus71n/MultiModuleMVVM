package com.kimboo.postdetail.di

import android.arch.lifecycle.ViewModel
import com.kimboo.core.di.component.BaseSubComponent
import com.kimboo.core.di.component.ViewModelKey
import com.kimboo.postdetail.ui.PostDetailViewModel
import com.kimboo.postdetail.ui.PostDetailActivityFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

/**
 * Created by Agustin Tomas Larghi on 12/6/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
@Singleton
@Component(modules = [(PostDetailModule::class)], dependencies = arrayOf(BaseSubComponent::class))
interface PostDetailComponent {

    fun injectFragment(postDetailActivityFragment: PostDetailActivityFragment)

    @Component.Builder
    interface Builder {
        fun baseSubComponent(baseSubComponent: BaseSubComponent): Builder
        fun build(): PostDetailComponent
    }
}

@Module
abstract class PostDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostDetailViewModel::class)
    abstract fun bindPosDetailViewModel(postDetailViewModel: PostDetailViewModel): ViewModel

}