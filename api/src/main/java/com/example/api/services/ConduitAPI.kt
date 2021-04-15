package com.example.api.services

import com.example.api.models.entities.UserCreds
import com.example.api.models.requests.LoginRequest
import com.example.api.models.requests.SignupRequest
import com.example.api.models.responses.ArticleResponse
import com.example.api.models.responses.ArticlesResponse
import com.example.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ConduitAPI {
    @POST("users")
    suspend fun signupUser(
        @Body userCreds:SignupRequest
    ):retrofit2.Response<UserResponse>

    @POST("users/login")
    suspend fun loginUser(
        @Body userCreds: LoginRequest
    ): Response<UserResponse>

    @GET("articles")
    suspend fun getArticles():retrofit2.Response<ArticlesResponse>

    @GET("articles/{slug}")
    suspend fun getArticleBySlug(
            @Path("slug") slug: String
    ): Response<ArticleResponse>
}