package com.chaozhang.jetreddit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.chaozhang.jetreddit.data.model.RedditListing
import com.chaozhang.jetreddit.data.repository.RedditRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val redditRepository: RedditRepository
) : ViewModel() {

  val reddits: LiveData<RedditListing> = redditRepository
    .topReddits
    .asLiveData(context = Dispatchers.IO)
}
