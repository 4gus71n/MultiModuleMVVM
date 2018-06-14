package com.kimboo.postmenu.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kimboo.postmenu.R
import kotlinx.android.synthetic.main.activity_post_menu.*

class PostMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_menu)
        setSupportActionBar(toolbar)

        postMenuFab.setOnClickListener { view ->
            startActivity(Intent(this@PostMenuActivity, Class.forName("com.kimboo.postdetail.PostDetailActivity")))
        }
    }

}
