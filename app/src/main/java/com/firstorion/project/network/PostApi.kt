package com.firstorion.project.network

import com.firstorion.project.repo.post.IPostsNetwork
import com.firstorion.project.repo.post.Post

class PostApi(private val api: IPostsNetwork){
    fun getAllPostsFromApi() = api.getAllPostsFromApi()
    fun createPost(post: Post) = api.createPost(post)
}