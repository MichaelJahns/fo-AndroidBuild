package com.firstorion.project.repo.post

import android.app.Application
import androidx.lifecycle.LiveData
import com.firstorion.project.database.PostDatabase
import com.firstorion.project.viewmodel.post.IPostsRepo

class PostRepository(application: Application) : IPostsRepo {
    private val postDao: IPostsDatabase
    private val allPosts: LiveData<List<Post>>

    init{
        val db = PostDatabase.getInstance(application)
        postDao = db!!.postDao()
        allPosts = postDao.getAllPosts()
    }

    override fun getAllPosts(): LiveData<List<Post>> {
        return postDao.getAllPosts()
    }

    override fun uploadPost(userId: Int, body: String, title: String) {
        TODO("Not yet implemented")
    }

    override fun deleteAllPosts() {
        TODO("Not yet implemented")
    }
}