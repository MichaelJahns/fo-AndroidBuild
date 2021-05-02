package com.firstorion.project.repo.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * This interface needs to be implemented in the `database` package.
 * Return types and parameters can be changed if needed.
 * More functions can be added if needed but please do not remove any function.
 * */
@Dao
interface IUsersDatabase {
    @Query("SELECT *FROM user_table")
    fun getAllUsers(): List<User>
    @Query("SELECT * FROM user_table WHERE userId = :userId")
    fun getUserWithId(userId: Int): LiveData<User>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(userList: List<User>)
    @Query("DELETE FROM user_table WHERE userId = :userId")
    fun deleteUserWithId(userId: Int)
    @Query("DELETE FROM user_table")
    fun deleteAllUsers()

}