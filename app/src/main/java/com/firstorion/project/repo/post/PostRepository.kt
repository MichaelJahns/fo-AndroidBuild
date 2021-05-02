package com.firstorion.project.repo.post

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.firstorion.project.database.PostDatabase
import com.firstorion.project.network.PostApi
import com.firstorion.project.network.RetrofitInstance
import com.firstorion.project.util.Toaster
import com.firstorion.project.viewmodel.post.IPostsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.await
import retrofit2.awaitResponse
import javax.security.auth.callback.Callback


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
        postDao.insertPost(Post(userId, 100 ,body, title))
    }

    override suspend fun uploadPosts(postList: List<Post>) {
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
                                uploadPosts(response.body()!!)
                            }
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("PostRepo", "Failed to make API call")            }

        })
    }

    // API METHODS
    fun getAllPostsFromApi() = postApi.getAllPostsFromApi()
}