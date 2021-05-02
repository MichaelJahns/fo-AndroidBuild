package com.firstorion.project.viewmodel.post

import androidx.lifecycle.LiveData
import com.firstorion.project.repo.post.Post

/**
 * This interface needs to be implemented in the `repo` package.
 *
 * When the Posts are downloaded from network, they need to be saved in the database.
 * After the Posts are saved, the app should receive them from the database instead of network for the next time.
 *
 * When the app (or Activity) is closed, the database should be cleared.
 *
 *
 * Return types and parameters of the functions can be changed if needed.
 * More functions can be added if needed but please do not remove any function.
 * */
interface IPostsRepo {

    fun getAllPosts(): LiveData<List<Post>>
    fun getAllPostsFromUserWithId(userId: Int): LiveData<List<Post>>
    suspend fun uploadPost(post: Post)
    suspend fun insertPosts(postList: List<Post>)
    suspend fun deleteAllPosts()

}