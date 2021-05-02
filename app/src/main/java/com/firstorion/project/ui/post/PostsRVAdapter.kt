package com.firstorion.project.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.firstorion.project.R
import com.firstorion.project.repo.post.Post

/**
 * Feel free to change as you wish
 * */
class PostsRVAdapter(
    private var postClickedListener: OnPostClickedListener,
    private val postList: List<Post>
): RecyclerView.Adapter<PostsRVAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.postTitle.text = postList[position].title
        holder.postBody.text = postList[position].body
        holder.postUserId.text = postList[position].userId.toString()
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class PostViewHolder(v: View):
            RecyclerView.ViewHolder(v),
            View.OnClickListener
    {
        val postTitle: TextView = v.findViewById(R.id.tvPostTitle)
        val postBody: TextView = v.findViewById(R.id.tvPostBody)
        val postUserId: TextView = v.findViewById(R.id.tvPostUserId)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION){
                postClickedListener.onPostClicked(postList[position])
            }
        }
    }

    interface OnPostClickedListener{
        fun onPostClicked(post: Post)
    }
}