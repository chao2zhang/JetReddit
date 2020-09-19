package com.chaozhang.jetreddit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chaozhang.jetreddit.data.model.RedditListing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

  private val homeViewModel by viewModels<HomeViewModel>()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return ComposeView(requireContext()).apply {
      setContent {
        RedditListing(viewModel = homeViewModel)
      }
    }
  }
}

@Composable
fun RedditListing(viewModel: HomeViewModel) {
  val redditListings: RedditListing? by viewModel.reddits.observeAsState()

  redditListings?.data?.children?.let { reddits ->
    LazyColumnFor(items = reddits) { reddit ->
      Text(
        text = reddit.data.title,
        modifier = Modifier.padding(8.dp)
      )
    }
  }
}