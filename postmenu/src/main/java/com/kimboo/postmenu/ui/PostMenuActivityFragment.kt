package com.kimboo.postmenu.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimboo.core.MyApp
import com.kimboo.core.di.module.MyViewModelFactory
import com.kimboo.postmenu.R
import com.kimboo.postmenu.databinding.FragmentPostMenuBinding
import com.kimboo.postmenu.di.DaggerPostMenuComponent
import com.kimboo.postmenu.ui.adapters.PostMenuAdapter
import kotlinx.android.synthetic.main.fragment_post_menu.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class PostMenuActivityFragment : Fragment() {

    //region Variables declaration
    @Inject
    lateinit var viewModelProvider: MyViewModelFactory

    private lateinit var dataBinding: FragmentPostMenuBinding //Generated automatically
    private lateinit var viewModel: PostMenuViewModel

    private lateinit var postMenuAdapter: PostMenuAdapter
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

        postMenuRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        postMenuAdapter = PostMenuAdapter()
        postMenuRecyclerView.adapter = postMenuAdapter

        viewModel.networkEvents.observe(this, Observer { networkEvent ->
            Log.d("SARASA", networkEvent)
        })

        viewModel.galleryPostList.observe(this, Observer { imgurGalleryPosts ->
            imgurGalleryPosts?.let { postMenuAdapter.imgurGalleryPosts = it }
        })
    }
}
