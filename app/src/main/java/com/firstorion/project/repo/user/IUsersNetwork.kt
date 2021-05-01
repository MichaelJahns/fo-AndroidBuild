package com.firstorion.project.repo.user

/**
 *  The interface that will provide the network communication between https://jsonplaceholder.typicode.com/users and the app
 *
 * This interface needs to be implemented in the `network` package.
 * Return types and parameters of the functions can be changed if needed.
 * More functions can be added if needed but please do not remove any function.
 * */
interface IUsersNetwork {

    fun getUserWithId(userId: Int): User

}