package com.chaozhang.jetreddit.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.chaozhang.jetreddit.data.model.RedditListing
import com.chaozhang.jetreddit.data.repository.RedditRepository
import kotlinx.coroutines.Dispatchers

class HomeViewModel @ViewModelInject constructor(
  private val redditRepository: RedditRepository
) : ViewModel() {

  val reddits: LiveData<RedditListing>
    get() = redditRepository.topReddits.asLiveData(context = Dispatchers.IO)
}