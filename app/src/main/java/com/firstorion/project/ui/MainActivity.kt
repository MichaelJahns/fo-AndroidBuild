package com.firstorion.project.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.firstorion.project.R
import com.firstorion.project.network.PostApi
import com.firstorion.project.network.RetrofitInstance
import com.firstorion.project.ui.post.PostsFragment
import com.firstorion.project.ui.user.UserFragment
import com.firstorion.project.util.PostsViewModelFactory
import com.firstorion.project.viewmodel.post.PostsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.lang.Exception

class MainActivity :
    AppCompatActivity(),
    PostsFragment.tester{

    private lateinit var postFragment: PostsFragment
    private lateinit var userFragment: UserFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postFragment = PostsFragment()
        userFragment = UserFragment()
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, postFragment)
                .commit()
        }
    }

    override fun foo() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, userFragment)
            .commit()
    }

}