package com.kimboo.postdetail.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kimboo.core.model.ImgurGalleryPost
import com.kimboo.core.utils.MyAppIntents
import com.kimboo.postdetail.R
import kotlinx.android.synthetic.main.activity_post_detail.*

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        if (intent.extras.containsKey(MyAppIntents.ARG_IMGUR_GALLERY_POST)) {
            val imgurGalleryPost = intent.extras.getParcelable<ImgurGalleryPost>(MyAppIntents.ARG_IMGUR_GALLERY_POST)

            supportFragmentManager.beginTransaction()
                    .replace(R.id.postDetailFragmentHolder,
                            PostDetailActivityFragment.newInstance(imgurGalleryPost),
                            PostDetailActivityFragment.TAG)
                    .commit()
        }
    }

}
