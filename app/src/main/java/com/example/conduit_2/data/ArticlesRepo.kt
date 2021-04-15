package com.example.conduit_2.data

import com.example.api.ConduitClient

object ArticlesRepo {
    val api=ConduitClient.publicApi
    val authApi=ConduitClient.authApi
    suspend fun getGlobalFeed()=api.getArticles().body()?.articles
    suspend fun getMyfeed()= authApi.getFeedArticles().body()?.articles
}