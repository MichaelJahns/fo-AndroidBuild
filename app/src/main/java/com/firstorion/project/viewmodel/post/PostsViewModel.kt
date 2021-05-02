package com.firstorion.project.viewmodel.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.firstorion.project.repo.post.Post

import com.firstorion.project.repo.post.PostRepository
import com.firstorion.project.util.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

/**
 * This class will get the `Post` data from `postRepo` that will be displayed on `PostsFragment` fragment.
 *
 * Please do not remove postsRepo from the constructor.
 * */
class PostsViewModel(
    private var postsRepository: PostRepository
) : ViewModel() {
    fun getPosts() :LiveData<List<Post>> {
        return postsRepository.getAllPosts()
    }
    fun getAllPostsFromUserWithId(userId: Int): LiveData<List<Post>>{
        return postsRepository.getAllPostsFromUserWithId(userId)
    }
    suspend fun insertPost(post: Post){
        postsRepository.uploadPost(post)
    }
}