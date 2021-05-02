package com.firstorion.project.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firstorion.project.R
import com.firstorion.project.network.RetrofitInstance
import com.firstorion.project.ui.post.PostsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCurrentData()

        // You can remove this if you want to use another approach
        if(savedInstanceState == null){
            val postFragment = PostsFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, postFragment)
                .commit()
        }

    }
    private fun getCurrentData() {
        val api = RetrofitInstance.api
        Log.e("MAINACT", "Attempting retrofit")

        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = api.getAllPostsFromAllUsers().awaitResponse()
                if(response.isSuccessful){
                    val data = response.body()!!
                    Log.e("MAINACT", data[0].title)
                }else{
                    var exception: Exception = Exception()
                    throw exception
                }
            }catch (exception: Exception){
                Log.e("MAINACT", exception.message.toString())
            }
        }
    }


}