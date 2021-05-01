package com.firstorion.project.database

import android.app.Application
import androidx.room.Room

class PostDatabaseProvider : Application() {
    companion object{
        var database: PostDatabase?= null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            PostDatabase::class.java,
            "post_db" )
            .fallbackToDestructiveMigration()
            .build()
    }
}