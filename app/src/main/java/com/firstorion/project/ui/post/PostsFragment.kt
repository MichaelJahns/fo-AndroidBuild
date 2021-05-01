package com.firstorion.project.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstorion.project.R
import com.firstorion.project.repo.post.Post

class PostsFragment : Fragment(), PostsRVAdapter.OnPostClickedListener {

    private val postsAdapter = PostsRVAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_posts, container, false)

        val rv = v.findViewById<RecyclerView>(R.id.postsRecyclerView)
        rv.layoutManager = LinearLayoutManager(v.context)
        rv.adapter = postsAdapter

        return v
    }


    override fun onPostClicked(post: Post) {
        TODO("Not yet implemented")
    }
}