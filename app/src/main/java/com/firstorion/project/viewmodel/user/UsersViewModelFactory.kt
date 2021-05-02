package com.firstorion.project.viewmodel.user

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firstorion.project.network.UserApi
import com.firstorion.project.repo.post.PostRepository
import com.firstorion.project.repo.user.UserRepository
import com.firstorion.project.viewmodel.post.PostsViewModel

class UsersViewModelFactory(
    private val mApplication: Application,
    private val userApi: UserApi
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UsersViewModel(UserRepository(mApplication, userApi)) as T
    }
}