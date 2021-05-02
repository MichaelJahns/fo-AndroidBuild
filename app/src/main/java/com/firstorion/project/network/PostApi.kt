package com.firstorion.project.network

import com.firstorion.project.repo.post.IPostsNetwork

class PostApi(private val api: IPostsNetwork){
    fun getAllPostsFromApi() = api.getAllPostsFromAllUsers()
}