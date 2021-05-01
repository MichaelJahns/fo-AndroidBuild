package com.firstorion.project.repo.post

/**
 * This interface needs to be implemented in the `database` package.
 * Return types and parameters can be changed if needed.
 * More functions can be added if needed but please do not remove any function.
 * */
interface IPostsDatabase {

    fun getAllPosts(): List<Post>
    fun savePosts(posts: List<Post>)
    fun savePost(post: Post)
    fun deleteAllPost()
    fun deletePostWithId(postId: Int)

}