package com.firstorion.project.ui.post

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.firstorion.project.network.PostApi
import com.firstorion.project.network.RetrofitInstance
import com.firstorion.project.repo.post.Post
import com.firstorion.project.util.PostsViewModelFactory
import com.firstorion.project.viewmodel.post.PostsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostsFragment : Fragment(), PostsRVAdapter.OnPostClickedListener{
    private var mHandler: Handler = Handler(Looper.getMainLooper())
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
        initializeUI(view)

        postsViewModelFactory = PostsViewModelFactory(
            activity!!.application,
            PostApi(RetrofitInstance.api))
        postViewModel = ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)
        setUpObservers()
        return view
    }

    private fun setUpObservers() {
        postViewModel.getPosts().observe(viewLifecycleOwner, Observer { postList ->
            updateUI(postList)
        })
    }

    private fun initializeUI(view: View){
        recyclerView = view.findViewById(R.id.postsRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =LinearLayoutManager(activity!!.applicationContext)
        updateUI(listOf<Post>())
        testButton = view.findViewById(R.id.button)
        testButton.setOnClickListener(View.OnClickListener {
            GlobalScope.launch(Dispatchers.IO){
                postViewModel.insertPost()
            }
        })
    }

    private fun updateUI(postList: List<Post>){
        val adapter = PostsRVAdapter(this, postList)
        recyclerView.adapter = adapter
    }

    override fun onPostClicked(post: Post) {
        Log.d("OnClick", "Clicked Post ${post.postId}")
    }

}