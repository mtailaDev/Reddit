package com.example.matthewtaila.redditstockx.feed.model


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.matthewtaila.redditstockx.R
import com.example.matthewtaila.redditstockx.databinding.ListItemRedditPostBinding

class PostFeedAdapter(val postFeed: PostFeed, val mListener: PostSelectedListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = DataBindingUtil.inflate<ListItemRedditPostBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_reddit_post,
            parent,
            false
        )
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postFeed.postDataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as PostViewHolder
        vh.bind(postFeed.postDataList[position].map())
        vh.itemBinding.root.setOnClickListener {
            mListener.onPostSelected(postFeed.postDataList[position])
        }
    }

    inner class PostViewHolder(val itemBinding: ListItemRedditPostBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(post: UIPost) {
            itemBinding.uiPost = post
        }
    }

    interface PostSelectedListener {
        fun onPostSelected(post: Post)
    }

}
