package com.firstorion.project.repo.post

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
interface IPostsDatabase {

    @Query("SELECT * FROM post_table")
    fun getAllPosts(): LiveData<List<Post>>

}