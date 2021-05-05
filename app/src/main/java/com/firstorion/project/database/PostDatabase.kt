package com.firstorion.project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.firstorion.project.repo.post.IPostsDatabase
import com.firstorion.project.repo.post.Post

@Database(entities = [(Post::class)], version = 3)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao() : IPostsDatabase

    companion object{

        @Volatile
        private var INSTANCE: PostDatabase ?= null

        fun getInstance(context: Context): PostDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        PostDatabase::class.java,
                        "post_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}