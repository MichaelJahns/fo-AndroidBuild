package com.firstorion.project.repo.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val name: String,
    @SerializedName(value = "username")
    val userName: String,
    val email: String
    // More fields can be added as needed
)
