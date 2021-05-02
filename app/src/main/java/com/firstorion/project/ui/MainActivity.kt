package com.firstorion.project.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.firstorion.project.R
import com.firstorion.project.network.RetrofitInstance
import com.firstorion.project.network.UserApi
import com.firstorion.project.ui.post.PostsFragment
import com.firstorion.project.ui.user.UserFragment
import com.firstorion.project.viewmodel.user.UsersViewModel
import com.firstorion.project.viewmodel.user.UsersViewModelFactory

class MainActivity :
    AppCompatActivity(){

    private lateinit var postFragment: PostsFragment
    private lateinit var userViewModelFactory: UsersViewModelFactory
    private lateinit var userViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModelFactory = UsersViewModelFactory(
            application,
            UserApi(RetrofitInstance.userApi)
        )
        userViewModel = ViewModelProvider(this, userViewModelFactory).get(UsersViewModel::class.java)
        postFragment = PostsFragment(userViewModel)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, postFragment)
                .commit()
        }
    }
}