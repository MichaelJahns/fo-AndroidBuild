package com.firstorion.project.repo.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val name: String,
    val userName: String,
    val email: String
    // More fields can be added as needed
)
