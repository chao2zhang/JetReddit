package com.chaozhang.jetreddit.data.service

import com.chaozhang.jetreddit.data.model.RedditListing
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {

    @GET("/top.json")
    suspend fun top(
        @Query("limit") limit: Int = 10
    ): RedditListing

    @GET("/hot.json")
    suspend fun hot(
        @Query("limit") limit: Int = 10
    ): RedditListing

    @GET("/best.json")
    suspend fun best(
        @Query("limit") limit: Int = 10
    ): RedditListing
}
