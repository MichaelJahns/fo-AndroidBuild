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
import com.firstorion.project.network.PostApi
import com.firstorion.project.network.RetrofitInstance
import com.firstorion.project.repo.post.Post
import com.firstorion.project.util.PostsViewModelFactory
import com.firstorion.project.util.Status
import com.firstorion.project.util.Toaster
import com.firstorion.project.viewmodel.post.PostsViewModel

class PostsFragment : Fragment(), PostsRVAdapter.OnPostClickedListener{


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
        postsViewModelFactory = PostsViewModelFactory(
            activity!!.application,
            PostApi(RetrofitInstance.api))
        postViewModel = ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)
        bindUI(view)


        setupObservers()
        return view
    }

    private fun setupObservers() {
       postViewModel.getAllPosts().observe(viewLifecycleOwner, Observer {
           it?.let { resource ->
           when(resource.status){
               Status.SUCCESS -> {
                   recyclerView.visibility = View.VISIBLE
//                   progressBar.visibility = View.GONE
                   resource.data?.let { posts -> retrieveList(posts) }
               }
               Status.ERROR -> {
                   recyclerView.visibility = View.VISIBLE
//                   progressBar.visibility = View.GONE
               }
               Status.LOADING -> {
//                   progressBar.visibility = View.VISIBLE
                   recyclerView.visibility = View.GONE
               }
           }

       }
       })
    }

    private fun retrieveList(posts: List<Post>) {
        setupRecyclerView(posts)
    }

    private fun bindUI(view: View){
        testButton = view.findViewById(R.id.button)
        testButton.setOnClickListener(View.OnClickListener {
            Toaster.futureToast(activity!!.applicationContext);
        })
        recyclerView = view.findViewById(R.id.postsRecyclerView)
    }
    private fun setupRecyclerView(postList: List<Post>){
        postsAdapter = PostsRVAdapter(this, postList)
        recyclerView.adapter = postsAdapter
        postsAdapter.setListener(object : PostsRVAdapter.OnPostClickedListener{
            override fun onPostClicked(post: Post) {
                Log.e("POSTFRAG", "Clicked")
                Toaster.futureToast(activity!!.applicationContext)
            }

        })
        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
    }

    override fun onPostClicked(post: Post) {
        Log.e("POSTFRAG", "Clicked")
        Toaster.futureToast(activity!!.applicationContext)
    }

}