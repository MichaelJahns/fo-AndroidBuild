package com.firstorion.project.viewmodel.user

import com.firstorion.project.repo.user.User

/**
 * This interface needs to be implemented in the `repo` package.
 *
 * When a User is downloaded from network, it needs to be saved in the database.
 * After the User is saved, the app should receive it from the database instead of network for the next time.
 *
 * When the app (or Activity) is closed, the database should be cleared.
 *
 *
 * Return types and parameters of the functions can be changed if needed.
 * More functions can be added if needed but please do not remove any function.
 * */
interface IUsersRepo {

    fun getUserWithId(userId: Int): User?
    fun deleteAllUsers()
}