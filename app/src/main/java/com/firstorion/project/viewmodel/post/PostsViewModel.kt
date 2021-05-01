package com.firstorion.project.viewmodel.post

import androidx.lifecycle.ViewModel

/**
 * This class will get the `Post` data from `postRepo` that will be displayed on `PostsFragment` fragment.
 *
 * Please do not remove postsRepo from the constructor.
 * */
class PostsViewModel(
    private val postsRepo: IPostsRepo
) : ViewModel() {

    // TODO Add your implementation here
}