package com.kimboo.postmenu.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimboo.core.MyApp
import com.kimboo.postmenu.R
import com.kimboo.postmenu.databinding.FragmentPostMenuBinding
import com.kimboo.postmenu.di.DaggerPostMenuComponent
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class PostMenuActivityFragment : Fragment() {

    //region Variables declaration
    //lateinit var viewModelFactory ¿ //region ViewModel DI declaration

    @Inject
    lateinit var viewModelProvider: PostMenuViewModel.ViewModelProvider

    private lateinit var dataBinding: FragmentPostMenuBinding //Generated automatically
    private lateinit var viewModel: PostMenuViewModel
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerPostMenuComponent.builder().baseSubComponent(MyApp.instance.provideBaseSubComponent()).build()
                .injectFragment(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding = FragmentPostMenuBinding.bind(view)
        viewModel = ViewModelProviders.of(this, viewModelProvider).get(PostMenuViewModel::class.java)
        dataBinding.viewModel = viewModel

        viewModel.uiEvents.observe(this, Observer {
            //TODO REMOVE
            Snackbar.make(getView()!!, it!!, Snackbar.LENGTH_LONG).show()
        })
    }
}
