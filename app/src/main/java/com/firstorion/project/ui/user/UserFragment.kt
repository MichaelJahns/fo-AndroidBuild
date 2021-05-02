package com.firstorion.project.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
    private var postsViewModel: PostsViewModel,
    private var usersViewModel: UsersViewModel
) : Fragment(), PostsRVAdapter.OnPostClickedListener{
//    XML VIEWS
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvName: TextView
    private lateinit var tvUserName: TextView
    private lateinit var tvEmail: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_user, container, false)
        initializeUI(view)
        val bundle = this.arguments
        val userId = bundle!!.getInt("userId")
        setupObservers(userId)
        GlobalScope.launch(Dispatchers.IO){
            usersViewModel.getUserWithId(userId)
        }
        return view
    }

    private fun initializeUI(view: View){
        tvName = view.findViewById(R.id.tvName)
        tvUserName = view.findViewById(R.id.tvUserName)
        tvEmail = view.findViewById(R.id.tvUserEmail)
        recyclerView = view.findViewById(R.id.usersRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        updateUI(listOf<Post>())
    }
    private fun updateUI(postList: List<Post>) {
        val adapter = PostsRVAdapter(this, postList)
        recyclerView.adapter = adapter
    }
    private fun updateUser(user: User){
        tvName.text = user.name
        tvUserName.text = user.userName
        tvEmail.text = user.email
    }

    private fun setupObservers(userId: Int){
        postsViewModel.getAllPostsFromUserWithId(userId).observe(viewLifecycleOwner, Observer { postList ->
            updateUI(postList)
        })
        usersViewModel.getActiveUser().observe(viewLifecycleOwner, Observer { user ->
            if(user != null) {
                updateUser(user)
            }
        })
    }
    override fun onPostClicked(post: Post) {
        activity?.supportFragmentManager?.popBackStack()
        Log.e("UserFragment", "I've been clicked")
    }

}