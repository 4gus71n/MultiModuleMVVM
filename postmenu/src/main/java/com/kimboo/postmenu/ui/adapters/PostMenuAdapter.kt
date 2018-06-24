package com.kimboo.postmenu.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.kimboo.core.di.module.GlideApp
import com.kimboo.core.di.module.GlideRequests
import com.kimboo.core.model.ImgurGalleryPost
import com.kimboo.postmenu.databinding.ItemViewPostMenuBinding
import kotlinx.android.synthetic.main.item_view_post_menu.view.*
import java.util.ArrayList

class PostMenuAdapter() : RecyclerView.Adapter<PostMenuViewHolder>() {

    //region Variables declaration
    var imgurGalleryPosts : List<ImgurGalleryPost> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    //endregion

    //region RecyclerView.Adapter methods implementation
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

    override fun onViewDetachedFromWindow(holder: PostMenuViewHolder) {
        holder.onClear()
    }
    //endregion
}


//region ViewHolder declaration
class PostMenuViewHolder(val itemBindings: ItemViewPostMenuBinding) : RecyclerView.ViewHolder(itemBindings.root) {
    fun onBindModel(imgurPost: ImgurGalleryPost) {
        itemBindings.viewModel = PostMenuItemViewModel(imgurPost)
        itemBindings.executePendingBindings()
    }

    fun onClear() {
        GlideApp.with(itemBindings.root).clear(itemBindings.root.postMenuItemImageView)
    }

}
//endregion