package com.firstorion.project.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firstorion.project.R
import com.firstorion.project.ui.post.PostsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // You can remove this if you want to use another approach
        if(savedInstanceState == null){
            val postFragment = PostsFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, postFragment)
                .commit()
        }

    }
}