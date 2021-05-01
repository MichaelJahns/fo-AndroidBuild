package com.firstorion.project.ui.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstorion.project.R
import com.firstorion.project.repo.post.Post
import com.firstorion.project.util.PostsViewModelFactory
import com.firstorion.project.util.Toaster
import com.firstorion.project.viewmodel.post.PostsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostsFragment : Fragment(), PostsRVAdapter.OnPostClickedListener {

    private lateinit var postsAdapter: PostsRVAdapter
    private lateinit var postViewModel: PostsViewModel
    private lateinit var postsViewModelFactory: PostsViewModelFactory
//    XML Views
    private lateinit var recyclerView: RecyclerView
    private lateinit var testButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_posts, container, false)
        postsViewModelFactory = PostsViewModelFactory(activity!!.application)
        postViewModel = ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)
        bindUI(view)
        setupObservers()
        return view
    }

    private fun setupObservers() {
       postViewModel.getAllPosts().observe(viewLifecycleOwner, Observer<List<Post>> { postList ->
           setupRecyclerView(postList)
           Log.e("POSTFRAG", "Setup Observers")
           if(postList.isNotEmpty()){
               Log.e("POSTFRAG", postList[0].title)
           }
       })
    }

    private fun bindUI(view: View){
        testButton = view.findViewById(R.id.button)
        testButton.setOnClickListener(View.OnClickListener {
            Toaster.futureToast(activity!!.applicationContext);
            GlobalScope.launch {
                postViewModel.insertPost()
            }
        })
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

}