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
import com.firstorion.project.util.Toaster

class PostsFragment : Fragment(), PostsRVAdapter.OnPostClickedListener {

    private val postsAdapter = PostsRVAdapter(this)
//    XML Views
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_posts, container, false)
        bindUI(view)
        setupRecyclerView()
        return view
    }

    private fun bindUI(view: View){
        recyclerView = view.findViewById(R.id.postsRecyclerView)
    }
    private fun setupRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        recyclerView.adapter = postsAdapter
    }

    override fun onPostClicked(post: Post) {
        Toaster.futureToast(activity!!.applicationContext)
        TODO("Not yet implemented")
//        get Post userID
//        get UserInfo from userID
//        Navigate away from this activity to another
    }
}