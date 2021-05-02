package com.firstorion.project.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firstorion.project.database.PostDatabase_Impl
import com.firstorion.project.network.PostApi
import com.firstorion.project.repo.post.PostRepository
import com.firstorion.project.viewmodel.post.PostsViewModel

class PostsViewModelFactory(
    private val mApplication: Application,
    private val postApi: PostApi
) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsViewModel(PostRepository(mApplication, postApi)) as T
    }
}