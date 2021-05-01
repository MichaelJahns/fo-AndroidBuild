package com.firstorion.project.repo.post

import androidx.lifecycle.LiveData
import com.firstorion.project.database.PostDatabaseProvider
import com.firstorion.project.viewmodel.post.IPostsRepo

class PostRepository : IPostsRepo {
    companion object{
        var postDao = PostDatabaseProvider.database!!.postDao()
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