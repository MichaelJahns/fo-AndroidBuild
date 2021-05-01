package com.firstorion.project.repo.user

/**
 * This interface needs to be implemented in the `database` package.
 * Return types and parameters can be changed if needed.
 * More functions can be added if needed but please do not remove any function.
 * */
interface IUsersDatabase {

    fun getUserWithId(userId: Int): User?
    fun saveUser(user: User)
    fun deleteUserWithId(userId: Int)
    fun deleteAllUsers()

}