package com.firstorion.project.ui.post

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firstorion.project.repo.post.Post

/**
 * Feel free to change as you wish
 * */
class PostsRVAdapter(
    private val postClickedListener: OnPostClickedListener
): RecyclerView.Adapter<PostsRVAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class PostViewHolder(v: View): RecyclerView.ViewHolder(v){

    }

    interface OnPostClickedListener{
        fun onPostClicked(post: Post)
    }


}