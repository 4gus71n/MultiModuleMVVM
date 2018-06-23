package com.kimboo.postmenu.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kimboo.core.model.ImgurGalleryPost
import com.kimboo.postmenu.databinding.ItemViewPostMenuBinding
import java.util.ArrayList

class PostMenuAdapter : RecyclerView.Adapter<PostMenuViewHolder>() {

    var imgurGalleryPosts : List<ImgurGalleryPost> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostMenuViewHolder {
        val itemBindings = ItemViewPostMenuBinding.inflate(LayoutInflater.from(parent.context))
        return PostMenuViewHolder(itemBindings)
    }

    override fun getItemCount(): Int = imgurGalleryPosts.size

    override fun onBindViewHolder(holder: PostMenuViewHolder, position: Int) {
        imgurGalleryPosts.getOrNull(position)?.let { imgurPost ->
            holder.onBindModel(imgurPost)
        } ?: holder.onClear()
    }
}

class PostMenuViewHolder(val itemBindings: ItemViewPostMenuBinding) : RecyclerView.ViewHolder(itemBindings.root) {
    fun onBindModel(imgurPost: ImgurGalleryPost) {
        itemBindings.viewModel = PostMenuItemViewModel(imgurPost)
        itemBindings.executePendingBindings()
    }

    fun onClear() {

    }

}