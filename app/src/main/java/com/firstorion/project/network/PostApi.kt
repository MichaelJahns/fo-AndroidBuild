package com.firstorion.project.network

import com.firstorion.project.repo.post.IPostsNetwork

class PostApi(private val api: IPostsNetwork){
    suspend fun getAllPostsFromApi() = api.getAllPostsFromAllUsers()
}