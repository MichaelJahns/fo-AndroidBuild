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
import com.firstorion.project.util.Toaster
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
    private lateinit var courierPost: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_posts, container, false)
        postsViewModelFactory = PostsViewModelFactory(
            activity!!.application,
            PostApi(RetrofitInstance.api))
        postViewModel = ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)
        initializeUI(view)
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
        courierPost = view.findViewById(R.id.courierMakePost)
        courierPost.setOnClickListener(View.OnClickListener{
            GlobalScope.launch(Dispatchers.IO){
                postViewModel.insertPost()
                Log.e("postfrag", "attempted")
            }
        })
        testButton = view.findViewById(R.id.button)
        testButton.setOnClickListener(View.OnClickListener {

        })
        updateUI(listOf<Post>())
    }

    private fun updateUI(postList: List<Post>){
        val adapter = PostsRVAdapter(this, postList)
        recyclerView.adapter = adapter
    }

    override fun onPostClicked(post: Post) {
        Toaster.makeToast(activity!!.applicationContext, "Clicked upon post: ${post.postId}")
    }
}