package com.firstorion.project.repo.user

import com.firstorion.project.viewmodel.user.IUsersRepo

class UserRepository(


): IUsersRepo
{
    override fun getAllUsers() {
        TODO("Not yet implemented")
    }

    override suspend fun getUserWithId(userId: Int): User? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllUsers() {
        TODO("Not yet implemented")
    }
}