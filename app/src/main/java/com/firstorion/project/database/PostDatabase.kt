package com.firstorion.project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Post::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract val postDatabase: PostDatabase

    companion object{

        @Volatile
        private var INSTANCE: PostDatabase? = null

        fun getInstance(context: Context): PostDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
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