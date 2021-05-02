package com.firstorion.project.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firstorion.project.R
import com.firstorion.project.ui.post.PostsFragment
import com.firstorion.project.ui.user.UserFragment

class MainActivity :
    AppCompatActivity(){

    private lateinit var postFragment: PostsFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postFragment = PostsFragment()
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, postFragment)
                .commit()
        }
    }
}