package com.firstorion.project.viewmodel.post

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.firstorion.project.repo.post.IPostsDatabase
import com.firstorion.project.repo.post.Post
import com.firstorion.project.repo.post.PostRepository

/**
 * This class will get the `Post` data from `postRepo` that will be displayed on `PostsFragment` fragment.
 *
 * Please do not remove postsRepo from the constructor.
 * */
class PostsViewModel(application: Application) : ViewModel() {
    var postsRepo: PostRepository = PostRepository(application)

    fun getAllPosts(): LiveData<List<Post>> {
        return postsRepo.getAllPosts()
    }
    suspend fun insertPost(){
        postsRepo.uploadPost(10, "Or maybe something old", "Dreaming of something new")
    }
    fun getPostsFromApi(){
        // TODO Add your implementation here
    }
}