package com.kimboo.postmenu.di

import com.kimboo.core.di.component.BaseSubComponent
import com.kimboo.core.repository.ImgurRepository
import com.kimboo.postmenu.ui.PostMenuActivityFragment
import com.kimboo.postmenu.ui.PostMenuViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Agustin Tomas Larghi on 12/6/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
@Singleton
@Component(modules = arrayOf(PostMenuModule::class), dependencies = arrayOf(BaseSubComponent::class))
interface PostMenuComponent {

    fun injectFragment(postMenuActivityFragment: PostMenuActivityFragment)

    @Component.Builder
    interface Builder {
        fun baseSubComponent(baseSubComponent: BaseSubComponent): Builder
        fun build(): PostMenuComponent
    }
}

@Module
class PostMenuModule {

    @Provides
    fun provideViewModelProvider(imgurRepository: ImgurRepository) : PostMenuViewModel.ViewModelProvider {
        return PostMenuViewModel.ViewModelProvider(imgurRepository)
    }

}