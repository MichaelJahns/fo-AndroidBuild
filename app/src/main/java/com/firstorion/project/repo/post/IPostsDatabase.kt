package com.firstorion.project.repo.post

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
    fun getAllPosts(): List<Post>
    @Insert
    fun savePost(post: Post)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun savePosts(posts: List<Post>)
    @Query("DELETE FROM post_table WHERE postId = :postId")
    fun deletePostWithId(postId: Int)
    @Query("DELETE FROM post_table")
    fun deleteAllPost()

}