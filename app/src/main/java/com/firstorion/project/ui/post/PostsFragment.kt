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
import com.firstorion.project.util.Status
import com.firstorion.project.util.Toaster
import com.firstorion.project.viewmodel.post.PostsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.awaitResponse
import java.lang.Exception

class PostsFragment : Fragment(), PostsRVAdapter.OnPostClickedListener{
    private var mHandler: Handler = Handler(Looper.getMainLooper())


    private lateinit var postAdapter: PostsRVAdapter
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
        bindUI(view)
        Log.e("Early Bind"," Message")
        postsViewModelFactory = PostsViewModelFactory(
            activity!!.application,
            PostApi(RetrofitInstance.api))
        postViewModel = ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)
        getCurrentData()
        return view
    }

    private fun getCurrentData() {
        val api = RetrofitInstance.api
        Log.e("POSTFRAG", "Attempting retrofit")

        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = api.getAllPostsFromAllUsers().awaitResponse()
                if(response.isSuccessful){
                    val data = response.body()!!
                    mHandler.post(Runnable {
                        Log.e("SuckLess", data.isEmpty().toString())
                        retrieveList(data)
                    })
                }
            }catch (exception: Exception){
                Log.e("BIG SUCK", exception.toString())
            }
        }
    }


    private fun retrieveList(postList: List<Post>){
        val adapter =  PostsRVAdapter(this, postList)
        recyclerView.adapter = adapter
    }

    private fun bindUI(view: View){
        var emptyList = listOf<Post>()
        recyclerView = view.findViewById(R.id.postsRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =LinearLayoutManager(activity!!.applicationContext)
        val adapter = PostsRVAdapter(this, emptyList)
        recyclerView.adapter = adapter
        testButton = view.findViewById(R.id.button)
        testButton.setOnClickListener(View.OnClickListener {
            getCurrentData()
        })
    }

    override fun onPostClicked(post: Post) {
        Log.e("POSTFRAG", "Clicked")
        Toaster.futureToast(activity!!.applicationContext)
    }


}