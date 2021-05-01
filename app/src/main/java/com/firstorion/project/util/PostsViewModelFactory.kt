package com.firstorion.project.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firstorion.project.viewmodel.post.PostsViewModel

class PostsViewModelFactory(private val mApplication: Application) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsViewModel(mApplication) as T
    }
}