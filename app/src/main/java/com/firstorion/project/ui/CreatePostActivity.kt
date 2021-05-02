package com.firstorion.project.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.firstorion.project.R
import com.firstorion.project.repo.post.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.Body

class CreatePostActivity : AppCompatActivity() {
    private lateinit var submitPost: Button
    private lateinit var etPostTitle: EditText
    private lateinit var etPostBody: EditText

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_create_post)
        initializeUI()
    }

    private fun initializeUI(){
        etPostTitle = findViewById(R.id.etPostTitle)
        etPostBody = findViewById(R.id.etPostBody)
        submitPost = findViewById(R.id.submitPost)
        submitPost.setOnClickListener(View.OnClickListener{
            var tempPost = Post(12, null, etPostBody.text.toString(), etPostTitle.text.toString())
//            create truncated User Post, with dumby userId and null id
//            send truncated Post to Api
//            response Full Post with ids

            val intent = Intent()
            intent.putExtra("postTitle", etPostBody.text.toString())
            intent.putExtra("postBody", etPostTitle.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        })
    }
}