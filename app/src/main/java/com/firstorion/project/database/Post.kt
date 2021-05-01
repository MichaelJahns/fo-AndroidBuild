package com.firstorion.project.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class Post(
    @PrimaryKey(autoGenerate = true)
    var postId: Int,
    @ColumnInfo(name = "poster")
    var userId: Int,
    var title: String,
    var body: String
)
