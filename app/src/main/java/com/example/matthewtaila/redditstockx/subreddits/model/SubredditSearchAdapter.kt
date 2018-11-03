package com.example.matthewtaila.redditstockx.subreddits.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.matthewtaila.redditstockx.MainActivityViewModel
import com.example.matthewtaila.redditstockx.R
import com.example.matthewtaila.redditstockx.databinding.ListItemSubredditBinding

class SubredditSearchAdapter(val searchResults: SubredditSearchResult, val mainVM: MainActivityViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ListItemSubredditBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_subreddit,
            parent,
            false
        )
        return SubredditViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return searchResults.dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as SubredditViewHolder
        vh.bind(searchResults.dataList[position])
//        vh.itemBinding.root.setOnClickListener {
//            postFeed.postDataList[position].url?.let {
//                mainVM.handleSelectedPost(it)
//            }
//        }
    }

    inner class SubredditViewHolder(val itemBinding: ListItemSubredditBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(sub: Subreddit) {
            itemBinding.subreddit = sub
        }
    }
}





