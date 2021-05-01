package com.firstorion.project.repo.post

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val postId: Int,
    val body: String,
    val title: String
)