package com.firstorion.project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.firstorion.project.repo.post.IPostsDatabase
import com.firstorion.project.repo.post.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [(Post::class)], version = 1)
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
                        .addCallback(seedDatabaseCallback(context))
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
        private fun seedDatabaseCallback(context: Context) : Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    GlobalScope.launch(Dispatchers.IO){
                        val postDao = getInstance(context)!!.postDao()
                        postDao.insertPost(Post(0,0,"First", "a"))
                        postDao.insertPost(Post(1,1,"Second", "b"))
                        postDao.insertPost(Post(2,2,"Third", "c"))
                    }
                }
            }
        }
    }
}