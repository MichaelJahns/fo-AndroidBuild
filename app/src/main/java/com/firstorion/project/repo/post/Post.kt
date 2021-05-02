package com.firstorion.project.repo.post

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "post_table")
data class Post(
        @PrimaryKey(autoGenerate = true)
        val userId: Int,
        @SerializedName(value = "id")
        val postId: Int?,
        val body: String,
        val title: String
)