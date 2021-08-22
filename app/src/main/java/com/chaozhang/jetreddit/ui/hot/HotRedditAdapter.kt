package com.chaozhang.jetreddit.ui.hot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import coil.load
import com.chaozhang.jetreddit.data.model.Reddit
import com.chaozhang.jetreddit.databinding.RedditBinding

class HotRedditAdapter(val layoutInflater: LayoutInflater) : Adapter<HotFragmentViewHolder>() {

    var reddits: List<Reddit> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotFragmentViewHolder {
        return HotFragmentViewHolder(RedditBinding.inflate(layoutInflater))
    }

    override fun onBindViewHolder(holder: HotFragmentViewHolder, position: Int) {
        val reddit = reddits[position]
        with(holder.binding) {
            thumbnail.load(reddit.data.thumbnail)
            title.text = reddit.data.title
            username.text = reddit.data.author
            comment.text = "${reddit.data.num_comments} comments"
        }
    }

    override fun getItemCount(): Int = reddits.size
}