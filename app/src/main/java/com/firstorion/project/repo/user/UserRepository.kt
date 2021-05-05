package com.firstorion.project.repo.user

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.firstorion.project.database.UserDatabase
import com.firstorion.project.network.UserApi
import com.firstorion.project.repo.post.Post
import com.firstorion.project.viewmodel.user.IUsersRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import kotlin.math.atan

class UserRepository(
    application: Application,
    private val userApi: UserApi
): IUsersRepo {
    private val userDao: IUsersDatabase
    private var activeUser: LiveData<User>


    init {
        val db = UserDatabase.getInstance(application)
        userDao = db.userDao()
        apiCallAndPutInDB()
        activeUser = userDao.getUserWithId(0)
    }

    override fun getActiveUser(): LiveData<User> {
        return activeUser
    }

    override fun getAllUsers() {
        userDao.getAllUsers()
    }

    override fun getUserWithId(userId: Int): LiveData<User> {
        activeUser = userDao.getUserWithId(userId)
        return activeUser
    }

    override suspend fun insertUsers(userList: List<User>) {
        userDao.insertUsers(userList)
    }

    override suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    override suspend fun deleteUserById(userId: Int) {
        userDao.deleteUserWithId(userId)
    }

    private fun apiCallAndPutInDB(){
        getAllUsersFromApi().enqueue(object : retrofit2.Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful && response.body() != null) {
                    when (response.code()) {
                        200 -> {
                            GlobalScope.launch(Dispatchers.IO) {
                                insertUsers(response.body()!!)
                            }
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("UserRepository", "Failed to GET From API")
            }
        })
    }

//        API METHODS
    private fun getAllUsersFromApi() = userApi.getAllUsers()
    private fun getUserWithIdFromApi(userId: Int) = userApi.getUserWithId(userId)
}