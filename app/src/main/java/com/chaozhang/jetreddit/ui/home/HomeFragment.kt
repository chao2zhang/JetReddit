package com.chaozhang.jetreddit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chaozhang.jetreddit.data.model.Reddit
import com.chaozhang.jetreddit.data.model.RedditListing
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.coil.CoilImage

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
        MaterialTheme {
          RedditListing(viewModel = homeViewModel)
        }
      }
    }
  }
}

@Composable
fun RedditListing(viewModel: HomeViewModel) {
  val redditListings: RedditListing? by viewModel.reddits.observeAsState()
  val reddits = redditListings?.data?.children
  if (reddits != null) {
    LazyColumn {
      items(reddits) {
        RedditRow(reddit = it)
      }
    }
  }
}


@Composable
fun RedditRow(reddit: Reddit) {
  Column {
    if (reddit.data.url != null) {
      Surface(Modifier.heightIn(40.dp, 400.dp).fillMaxWidth()) {
        CoilImage(
          data = reddit.data.url,
          loading = {
            Box(Modifier.height(40.dp).fillMaxWidth()) {
              CircularProgressIndicator(
                Modifier
                  .height(20.dp)
                  .width(20.dp)
                  .align(Alignment.Center)
              )
            }
          }
        )
      }
    }
    Row {
      Text(
        text = reddit.data.title,
        fontSize = TextUnit.Sp(20),
        modifier = Modifier.padding(8.dp)
      )
    }
    Row {
      Text(
        text = "@${reddit.data.author}",
        color = MaterialTheme.colors.secondary,
        modifier = Modifier.padding(8.dp)
      )
      Text(
        text = "${reddit.data.num_comments} comment",
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(8.dp)
      )
    }
  }
}