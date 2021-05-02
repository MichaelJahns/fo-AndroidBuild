package com.firstorion.project.network

import com.firstorion.project.repo.post.IPostsNetwork
import com.firstorion.project.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: IPostsNetwork = getRetrofit().create(IPostsNetwork::class.java)
}