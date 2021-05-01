package com.firstorion.project.repo.post

data class Post(
    val userId: Int,
    val postId: Int,
    val body: String,
    val title: String
)