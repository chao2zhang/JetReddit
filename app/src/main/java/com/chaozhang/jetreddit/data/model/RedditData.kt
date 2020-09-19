package com.chaozhang.jetreddit.data.model

class RedditListing(
    val data: RedditListingData
)

class RedditListingData(
    val children: List<Reddit>,
    val after: String? = null,
    val before: String? = null
)

class Reddit(
    val data: RedditData
)

class RedditData(
    val author: String,
    val title: String,
    val num_comments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String?
)