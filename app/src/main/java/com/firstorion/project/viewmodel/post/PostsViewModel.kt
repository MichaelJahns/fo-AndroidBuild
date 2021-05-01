package com.firstorion.project.viewmodel.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.firstorion.project.repo.post.Post

/**
 * This class will get the `Post` data from `postRepo` that will be displayed on `PostsFragment` fragment.
 *
 * Please do not remove postsRepo from the constructor.
 * */
class PostsViewModel(
    private val postsRepo: IPostsRepo
) : ViewModel() {

    fun getAllPosts(): LiveData<List<Post>> {
        return postsRepo.getAllPosts()
    }
    fun getPostsFromApi(){
        // TODO Add your implementation here
    }
}