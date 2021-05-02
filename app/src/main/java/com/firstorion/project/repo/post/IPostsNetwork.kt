package com.firstorion.project.repo.post

import com.firstorion.project.repo.user.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * The interface that will provide the network communication between https://jsonplaceholder.typicode.com/posts and the app
 *
 * This interface needs to be implemented in the `network` package.
 * Return types and parameters of the functions can be changed if needed.
 * More functions can be added if needed but please do not remove any function.
 * */

interface IPostsNetwork {

    @GET("/posts")
    fun getAllPostsFromApi(): Call<List<Post>>

    @POST("/posts")
    fun createPost(@Body post:Post): Call<Post>

}