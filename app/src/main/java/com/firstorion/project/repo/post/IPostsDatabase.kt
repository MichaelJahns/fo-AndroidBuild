package com.firstorion.project.repo.post

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 * This interface needs to be implemented in the `database` package.
 * Return types and parameters can be changed if needed.
 * More functions can be added if needed but please do not remove any function.
 * */
@Dao
interface IPostsDatabase {

    @Query("SELECT * FROM post_table")
    fun getAllPosts(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfPosts(postList: List<Post>)

    @Query("DELETE FROM post_table")
    suspend fun deleteAllPosts()

    @Query("SELECT * FROM post_table WHERE userId = :userId")
    suspend fun getAllPostsByUser(userId: Int): List<Post>
}