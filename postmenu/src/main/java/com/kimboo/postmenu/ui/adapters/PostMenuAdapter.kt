package com.kimboo.postmenu.ui.adapters

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kimboo.core.model.ImgurGalleryPost
import com.kimboo.postmenu.databinding.ItemViewPostMenuBinding
import java.util.ArrayList

class PostMenuAdapter() : RecyclerView.Adapter<PostMenuViewHolder>() {

    //region Variables declaration
    var imgurGalleryPosts : List<ImgurGalleryPost> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val onClickEvents = MutableLiveData<Pair<PostMenuAdapterEvent, ImgurGalleryPost>>()

    enum class PostMenuAdapterEvent {
        GALLERY_CLICK, FAVORITE_CLICK
    }
    //endregion

    //region RecyclerView.Adapter methods implementation
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostMenuViewHolder {
        val itemBindings = ItemViewPostMenuBinding.inflate(LayoutInflater.from(parent.context))
        return PostMenuViewHolder(onClickEvents, itemBindings)
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
class PostMenuViewHolder(val callbackEvents: MutableLiveData<Pair<PostMenuAdapter.PostMenuAdapterEvent, ImgurGalleryPost>>,
                         val itemBindings: ItemViewPostMenuBinding) : RecyclerView.ViewHolder(itemBindings.root) {
    fun onBindModel(imgurPost: ImgurGalleryPost) {
        itemBindings.viewModel = PostMenuItemViewModel(callbackEvents, imgurPost)
        itemBindings.executePendingBindings()
    }

    fun onClear() {
        //GlideApp.with(itemBindings.root).clear(itemBindings.root.postMenuItemImageView)
    }

}
//endregion