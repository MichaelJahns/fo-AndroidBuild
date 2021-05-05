package com.firstorion.project.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.firstorion.project.R
import com.firstorion.project.network.PostApi
import com.firstorion.project.network.RetrofitInstance
import com.firstorion.project.network.UserApi
import com.firstorion.project.ui.post.PostsFragment
import com.firstorion.project.ui.user.UserFragment
import com.firstorion.project.util.Toaster
import com.firstorion.project.viewmodel.post.PostsViewModel
import com.firstorion.project.viewmodel.post.PostsViewModelFactory
import com.firstorion.project.viewmodel.user.UsersViewModel
import com.firstorion.project.viewmodel.user.UsersViewModelFactory

class MainActivity :
    AppCompatActivity(),
    PostsFragment.CourierToUserDetails{

    private lateinit var postFragment: PostsFragment
    private lateinit var userFragment: UserFragment

    private lateinit var postViewModel: PostsViewModel
    private lateinit var userViewModel: UsersViewModel

    private lateinit var postsViewModelFactory: PostsViewModelFactory
    private lateinit var userViewModelFactory: UsersViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postsViewModelFactory = PostsViewModelFactory(
            this.application,
            PostApi(RetrofitInstance.postApi)
        )
        userViewModelFactory = UsersViewModelFactory(
            application,
            UserApi(RetrofitInstance.userApi)
        )
        postViewModel = ViewModelProvider(this, postsViewModelFactory).get(PostsViewModel::class.java)
        userViewModel = ViewModelProvider(this, userViewModelFactory).get(UsersViewModel::class.java)
        postFragment = PostsFragment(this, postViewModel)
        userFragment = UserFragment(userViewModel, postViewModel)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, postFragment)
                .commit()
        }
    }

    override fun handleTransitionToUserDetails(userId: Int) {
        userViewModel.getUserWithId(userId)
        val bundle = Bundle()
        bundle.putInt("userId", userId)
        userFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, userFragment)
            .addToBackStack(null)
            .commit()
    }
}