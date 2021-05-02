package com.firstorion.project.ui.post

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.firstorion.project.R

class CreatePosActivity : AppCompatActivity() {
    private lateinit var submitPost: Button
    private lateinit var etPostTitle: EditText
    private lateinit var etPostBody: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_pos)
        initializeUI()
    }

    private fun initializeUI(){
        etPostTitle = findViewById(R.id.etPostTitle)
        etPostBody = findViewById(R.id.etPostBody)
        submitPost = findViewById(R.id.submitPost)
        submitPost.setOnClickListener(View.OnClickListener{
            val intent = Intent()
            intent.putExtra("postTitle", etPostBody.text.toString())
            intent.putExtra("postBody", etPostTitle.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        })
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}