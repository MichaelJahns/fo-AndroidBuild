package com.firstorion.project.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.firstorion.project.repo.user.User

/**
 * This class will get the `User` data from `usersRepo` that will be displayed on the `UsersFragment` fragment.
 *
 * Please do not remove `usersRepo` parameter from the constructor.
 * */
class UsersViewModel(
    private val usersRepo: IUsersRepo
) : ViewModel() {
    fun getActiveUser(): LiveData<User>{
        return usersRepo.getActiveUser()
    }
    fun getAllUsers(){
        usersRepo.getAllUsers()
    }
    suspend  fun deleteAllUsers(){
        usersRepo.deleteAllUsers()
    }
    fun getUserWithId(userId: Int) :LiveData<User>{
        return usersRepo.getUserWithId(userId)
    }

}