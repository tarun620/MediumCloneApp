package com.example.api.models.requests

import com.example.api.models.entities.UserUpdateData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserUpdateRequest(
        @Json(name = "user")
        val user: UserUpdateData
)