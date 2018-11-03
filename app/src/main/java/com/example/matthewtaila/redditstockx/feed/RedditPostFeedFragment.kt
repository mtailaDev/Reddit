package com.example.matthewtaila.redditstockx.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matthewtaila.redditstockx.MainActivityViewModel
import com.example.matthewtaila.redditstockx.R
import com.example.matthewtaila.redditstockx.databinding.FragmentRedditPostFeedBinding
import com.example.matthewtaila.redditstockx.feed.model.PostFeedAdapter
import kotlinx.android.synthetic.main.fragment_reddit_post_feed.*

class RedditPostFeedFragment : Fragment() {

    private lateinit var mBinding: FragmentRedditPostFeedBinding
    private lateinit var redditPostViewModel: RedditFeedViewModel
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        redditPostViewModel = ViewModelProviders.of(this)[RedditFeedViewModel::class.java]
        mainActivityViewModel = ViewModelProviders.of(activity!!)[MainActivityViewModel::class.java]
        observeLiveData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_reddit_post_feed, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        redditPostViewModel.getPosts()
    }

    fun observeLiveData() {
        redditPostViewModel.postList.observe(this, Observer {
            if (it.postDataList.isNotEmpty()){
                val mAdapter = PostFeedAdapter(it, mainActivityViewModel)
                feed_rv_posts.adapter = mAdapter
                feed_rv_posts.layoutManager = LinearLayoutManager(context)
            }
        })
        mainActivityViewModel.selectedURL.observe(this, Observer {
            Navigation.createNavigateOnClickListener(R.id.action_redditPostFeedFragment_to_detailedPostFragment, null).onClick(view)
        })
        mainActivityViewModel.subReddit.observe(this, Observer {
            Navigation.createNavigateOnClickListener(R.id.action_redditPostFeedFragment_to_subredditsResultFragment, null).onClick(view)
        })
    }

}