package com.firstorion.project.viewmodel.user

import androidx.lifecycle.ViewModel

/**
 * This class will get the `User` data from `usersRepo` that will be displayed on the `UsersFragment` fragment.
 *
 * Please do not remove `usersRepo` parameter from the constructor.
 * */
class UsersViewModel(
    private val usersRepo: IUsersRepo
) : ViewModel() {
    fun getAllUsers(){
        usersRepo.getAllUsers()
    }
    suspend  fun deleteAllUsers(){
        usersRepo.deleteAllUsers()
    }
    suspend fun getUserWithId(userId: Int){
        usersRepo.getUserWithId(userId)
    }

}