package com.chaozhang.jetreddit.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaozhang.jetreddit.Constants
import com.chaozhang.jetreddit.data.model.RedditListing
import com.chaozhang.jetreddit.data.repository.RedditRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val redditRepository: RedditRepository
) : ViewModel() {

  private val _reddits: MutableLiveData<RedditListing> = MutableLiveData()
  val reddits: LiveData<RedditListing> = _reddits

  init {
    viewModelScope.launch {
      Log.i(Constants.FLOW_TAG, "coroutine running...")
      redditRepository.topReddits.collect { redditListing ->
        Log.i(Constants.FLOW_TAG, "flow collecting...")
        _reddits.value = redditListing
      }
    }
  }
}
