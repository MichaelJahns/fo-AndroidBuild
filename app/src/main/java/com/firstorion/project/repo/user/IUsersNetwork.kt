package com.firstorion.project.repo.user

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *  The interface that will provide the network communication between https://jsonplaceholder.typicode.com/users and the app
 *
 * This interface needs to be implemented in the `network` package.
 * Return types and parameters of the functions can be changed if needed.
 * More functions can be added if needed but please do not remove any function.
 * */
interface IUsersNetwork {

    @GET("/users/")
    fun getUserWithId(@Path("userId") userId: Int): User
    @GET("/users/{userId}")
    fun getAllUsersFromApi(): Call<List<User>>

}