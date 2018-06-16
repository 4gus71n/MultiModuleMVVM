package com.kimboo.postmenu.di

import android.arch.lifecycle.ViewModel
import com.kimboo.core.di.component.BaseSubComponent
import com.kimboo.core.di.component.ViewModelKey
import com.kimboo.postmenu.ui.PostMenuActivityFragment
import com.kimboo.postmenu.ui.PostMenuViewModel
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
@Component(modules = [(PostMenuModule::class)], dependencies = arrayOf(BaseSubComponent::class))
interface PostMenuComponent {

    fun injectFragment(postMenuActivityFragment: PostMenuActivityFragment)

    @Component.Builder
    interface Builder {
        fun baseSubComponent(baseSubComponent: BaseSubComponent): Builder
        fun build(): PostMenuComponent
    }
}

@Module
abstract class PostMenuModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostMenuViewModel::class)
    abstract fun bindPostMenuViewModel(postMenuViewModel: PostMenuViewModel): ViewModel

}