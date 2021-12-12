package com.chaozhang.jetreddit.ui.composeables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import coil.compose.ImagePainter
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.chaozhang.jetreddit.data.model.Reddit
import com.chaozhang.jetreddit.data.model.RedditListing

@Composable
fun RedditListingUI(redditListings: LiveData<RedditListing>) {
    val redditListingsState: RedditListing? by redditListings.observeAsState()
    val reddits = redditListingsState?.data?.children
    if (reddits != null) {
        LazyColumn {
            items(
                count = reddits.size,
                itemContent = { index ->
                    RedditRow(reddit = reddits[index])
                }
            )
        }
    }
}

@Composable
fun RedditRow(reddit: Reddit) {
    Row {
        val thumbnail = reddit.data.thumbnail
        if (thumbnail != null) {
            val painter = rememberImagePainter(
                data = thumbnail,
                imageLoader = LocalImageLoader.current,
                builder = {
                    placeholder(0)
                }
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.height(120.dp).width(120.dp)
            )
        } else {
            Box(
                Modifier
                    .width(120.dp)
                    .height(120.dp))
        }
        Column {
            Row {
                Text(
                    text = reddit.data.title,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Row {
                Text(
                    text = "@${reddit.data.author}",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "${reddit.data.num_comments} comment",
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}