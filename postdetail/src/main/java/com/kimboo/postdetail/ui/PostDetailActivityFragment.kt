package com.kimboo.postdetail.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimboo.core.MyApp
import com.kimboo.core.di.module.MyViewModelFactory
import com.kimboo.core.model.ImgurGalleryPost
import com.kimboo.core.utils.MyAppIntents
import com.kimboo.postdetail.R
import com.kimboo.postdetail.databinding.FragmentPostDetailBinding
import com.kimboo.postdetail.di.DaggerPostDetailComponent
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class PostDetailActivityFragment : Fragment() {

    //region Static code declaration
    companion object {
        val TAG = PostDetailActivityFragment::class.simpleName

        fun newInstance(imgurGalleryPost: ImgurGalleryPost): PostDetailActivityFragment {
            val fragment = PostDetailActivityFragment()
            val args = Bundle()
            fragment.arguments = args
            args.putParcelable(MyAppIntents.ARG_IMGUR_GALLERY_POST, imgurGalleryPost)
            return fragment
        }
    }
    //endregion

    //region Variables declaration
    @Inject
    lateinit var viewModelProvider: MyViewModelFactory

    private lateinit var dataBinding: FragmentPostDetailBinding //Generated automatically
    private lateinit var viewModel: PostDetailViewModel
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerPostDetailComponent.builder().baseSubComponent(MyApp.instance.provideBaseSubComponent()).build()
                .injectFragment(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding = FragmentPostDetailBinding.bind(view)
        viewModel = ViewModelProviders.of(this, viewModelProvider).get(PostDetailViewModel::class.java)
        dataBinding.viewModel = viewModel

        arguments?.let { bundle ->
            bundle.getParcelable<ImgurGalleryPost>(MyAppIntents.ARG_IMGUR_GALLERY_POST)
        }?.let { imgurGalleryPost ->
            viewModel.imgurGalleryPost.set(imgurGalleryPost)
        }
    }
}
