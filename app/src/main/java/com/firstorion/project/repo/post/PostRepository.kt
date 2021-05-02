package com.firstorion.project.repo.post

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.firstorion.project.database.PostDatabase
import com.firstorion.project.network.PostApi
import com.firstorion.project.network.RetrofitInstance
import com.firstorion.project.viewmodel.post.IPostsRepo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PostRepository(
    application: Application,
    private val postApi: PostApi
    ) : IPostsRepo {
    private val postDao: IPostsDatabase
    private val allPosts: LiveData<List<Post>>

    init{
        val db = PostDatabase.getInstance(application)
        postDao = db!!.postDao()
//        before you pull allPosts, make the API request, and save it to the DB
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

    suspend fun getAllPostsFromApi() = postApi.getAllPostsFromApi()
}