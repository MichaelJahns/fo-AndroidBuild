package com.firstorion.project.network

import com.firstorion.project.repo.user.IUsersNetwork

class UserApi(private val api: IUsersNetwork) {
    fun getUserWithId(userId: Int) = api.getUserWithId(userId)
    fun getAllUsers() = api.getAllUsersFromApi()
}