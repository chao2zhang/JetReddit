package com.chaozhang.jetreddit.ui.hot

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.chaozhang.jetreddit.data.model.RedditListing
import com.chaozhang.jetreddit.data.repository.RedditRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HotViewModel @Inject constructor(
    private val redditRepository: RedditRepository
) : ViewModel() {

    val reddits: LiveData<RedditListing> = liveData {
        emit(redditRepository.hotReddits())
    }
}