package com.chaozhang.jetreddit.data.repository

import com.chaozhang.jetreddit.data.model.RedditListing
import com.chaozhang.jetreddit.data.service.RedditService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RedditRepository @Inject constructor(private val redditService: RedditService) {

    val topReddits: Flow<RedditListing> = flow {
        emit(redditService.top())
    }
}