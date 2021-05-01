package com.firstorion.project.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.firstorion.project.repo.post.IPostsDatabase
import com.firstorion.project.repo.post.Post

@Database(entities = [(Post::class)], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao() : IPostsDatabase
}