package com.firstorion.project.ui.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstorion.project.R
import com.firstorion.project.repo.post.Post
import com.firstorion.project.util.PostsViewModelFactory
import com.firstorion.project.util.Toaster
import com.firstorion.project.viewmodel.post.PostsViewModel

class PostsFragment : Fragment(), PostsRVAdapter.OnPostClickedListener {

    private lateinit var postsAdapter: PostsRVAdapter
    private lateinit var postViewModel: PostsViewModel
    private lateinit var postsViewModelFactory: PostsViewModelFactory
//    XML Views
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_posts, container, false)
        val list: List<Post> = getDumbyData()
        bindUI(view)
        postsAdapter = PostsRVAdapter(this, list)
        postsViewModelFactory = PostsViewModelFactory(activity!!.application)
        postViewModel = ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)
        setupObservers()
        return view
    }

    private fun setupObservers() {
       postViewModel.getAllPosts().observe(viewLifecycleOwner, Observer<List<Post>> { postList ->
           Log.e("POSTFRAG", postList.toString())
           setupRecyclerView(postList)
       })
    }

    private fun bindUI(view: View){
        recyclerView = view.findViewById(R.id.postsRecyclerView)
    }
    private fun setupRecyclerView(postList: List<Post>){
        postsAdapter = PostsRVAdapter(this, postList)
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
    private fun getDumbyData(): List<Post>{
        val p1 = Post(0,0,"First", "a")
        val p2 = Post(1,1,"Second", "b")
        val p3 = Post(2,2,"Third", "c")
        return listOf(p1, p2, p3)
    }

}