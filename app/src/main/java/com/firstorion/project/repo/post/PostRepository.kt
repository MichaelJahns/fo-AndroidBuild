package com.firstorion.project.repo.post

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.firstorion.project.database.PostDatabase
import com.firstorion.project.network.PostApi
import com.firstorion.project.viewmodel.post.IPostsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response


class PostRepository(
    application: Application,
    private val postApi: PostApi
    ) : IPostsRepo {
    private val postDao: IPostsDatabase
    private var allPosts: LiveData<List<Post>>

    init{
        val db = PostDatabase.getInstance(application)
        postDao = db.postDao()
        apiCallAndPutinDB()
        allPosts = postDao.getAllPosts()
    }

    override fun getAllPosts(): LiveData<List<Post>> {
        return postDao.getAllPosts()
    }

    override suspend fun uploadPost(userId: Int, body: String, title: String) {
        var tempPost = Post(userId, null, body, title)
        createPostFromApi(tempPost).enqueue(object : retrofit2.Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Log.e("Api", "pre IF")
                if(response.isSuccessful && response.body() != null) {
                    Log.e("Api", "${response.body()!!.postId}")
                    GlobalScope.launch(Dispatchers.IO) {
                        postDao.insertPost(response.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.e("PostRepository", "Failed to POST to API")
            }
        } )
    }

    override suspend fun insertPosts(postList: List<Post>) {
        postDao.insertListOfPosts(postList)
    }

    override suspend fun deleteAllPosts() {
        postDao.deleteAllPosts()
    }


    private fun apiCallAndPutinDB(){
        postApi.getAllPostsFromApi().enqueue(object : retrofit2.Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if(response.isSuccessful && response.body() != null) {
                    when (response.code()) {
                        200 -> {
                            GlobalScope.launch(Dispatchers.IO) {
                                insertPosts(response.body()!!)
                            }
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("PostRepository", "Failed to GET From API")
            }
        })
    }

    // API METHODS
    fun getAllPostsFromApi() = postApi.getAllPostsFromApi()
    fun createPostFromApi(post: Post) = postApi.createPost(post)
}