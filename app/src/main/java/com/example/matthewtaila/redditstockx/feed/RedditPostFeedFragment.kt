package com.example.matthewtaila.redditstockx.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.matthewtaila.redditstockx.R
import com.example.matthewtaila.redditstockx.databinding.FragmentRedditPostFeedBinding

class RedditPostFeedFragment : Fragment() {

    private lateinit var mBinding: FragmentRedditPostFeedBinding
    private lateinit var redditPostViewModel: RedditFeedViewModel

    companion object {
        @JvmStatic
        fun newInstance(): RedditPostFeedFragment {
            return RedditPostFeedFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        redditPostViewModel = ViewModelProviders.of(this)[RedditFeedViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_reddit_post_feed, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        redditPostViewModel.getPosts()
    }
}