package com.firstorion.project.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstorion.project.R
import com.firstorion.project.network.RetrofitInstance
import com.firstorion.project.network.UserApi
import com.firstorion.project.repo.post.Post
import com.firstorion.project.repo.user.User
import com.firstorion.project.ui.post.PostsRVAdapter
import com.firstorion.project.viewmodel.post.PostsViewModel
import com.firstorion.project.viewmodel.user.UsersViewModel
import com.firstorion.project.viewmodel.user.UsersViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserFragment(
    private var postsViewModel: PostsViewModel
) : Fragment(), PostsRVAdapter.OnPostClickedListener{

    private lateinit var userViewModel: UsersViewModel
    private lateinit var userViewModelFactory: UsersViewModelFactory
//    XML VIEWS
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_user, container, false)
        userViewModelFactory = UsersViewModelFactory(
            requireActivity().application,
            UserApi(RetrofitInstance.userApi)
        )
        userViewModel = ViewModelProvider(this, userViewModelFactory).get(UsersViewModel::class.java)
        initializeUI(view)
        val bundle = this.arguments
        val userId = bundle!!.getInt("userId")
        setupObservers(userId)
        GlobalScope.launch(Dispatchers.IO){
            userViewModel.getUserWithId(userId)
        }
        return view
    }
    private fun initializeUI(view: View){
        recyclerView = view.findViewById(R.id.usersRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        updateUI(listOf<Post>())
    }
    private fun updateUI(postList: List<Post>) {
        val adapter = PostsRVAdapter(this, postList)
        recyclerView.adapter = adapter
    }
    
    private fun setupObservers(userId: Int){
        postsViewModel.getAllPostsFromUserWithId(userId).observe(viewLifecycleOwner, Observer { postList ->
            updateUI(postList)
        })
    }
    override fun onPostClicked(post: Post) {
        Log.e("UserFragment", "I've been clicked")
    }

}