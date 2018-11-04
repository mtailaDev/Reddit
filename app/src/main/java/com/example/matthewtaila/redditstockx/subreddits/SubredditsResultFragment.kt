package com.example.matthewtaila.redditstockx.subreddits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matthewtaila.redditstockx.MainActivityViewModel
import com.example.matthewtaila.redditstockx.R
import com.example.matthewtaila.redditstockx.databinding.FragmentSubredditsResultFragmentBinding
import com.example.matthewtaila.redditstockx.subreddits.model.SubredditSearchAdapter
import com.example.matthewtaila.redditstockx.subreddits.model.SubredditSearchResult
import kotlinx.android.synthetic.main.fragment_subreddits_result_fragment.*


class SubredditsResultFragment : Fragment() {

    private lateinit var mBinding : FragmentSubredditsResultFragmentBinding
    private lateinit var subredditsSearchViewModel: SubredditsSearchViewModel
    private lateinit var mainActivityViewModel: MainActivityViewModel

    companion object {
        @JvmStatic
        fun newInstance() : SubredditsResultFragment{
            return SubredditsResultFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subredditsSearchViewModel = ViewModelProviders.of(this)[SubredditsSearchViewModel::class.java]
        mainActivityViewModel = ViewModelProviders.of(activity!!)[MainActivityViewModel::class.java]
        observeLiveData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_subreddits_result_fragment, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoadingState()
    }

    private fun showLoadingState() {
        subreddit_rv_results.visibility = View.INVISIBLE
        subreddit_tv_error.visibility = View.GONE
        subreddit_lav_loading.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        subredditsSearchViewModel.searchSubreddits(mainActivityViewModel.subReddit.value)
    }

    private fun observeLiveData() {
        subredditsSearchViewModel.subredditResults.observe(this, Observer {
            if (it.dataList.isNotEmpty()) {
                hideLoadingState()
                setupRecyclerView(it)
            }else {
                hideLoadingState()
                showEmptyErrorState()
            }

        })
        mainActivityViewModel.subReddit.observe(this, Observer {
            showLoadingState()
            subredditsSearchViewModel.searchSubreddits(it)
        })
    }

    private fun hideLoadingState() {
        subreddit_lav_loading.visibility = View.GONE
    }

    private fun setupRecyclerView(searchResult: SubredditSearchResult) {
        subreddit_rv_results.visibility = View.INVISIBLE
        subreddit_rv_results.alpha = 1f
        val mAdapter = SubredditSearchAdapter(searchResult, mainActivityViewModel)
        subreddit_rv_results.adapter = mAdapter
        subreddit_rv_results.layoutManager = LinearLayoutManager(context)
        subreddit_rv_results.visibility = View.VISIBLE
    }

    private fun showEmptyErrorState() {
        subreddit_rv_results.visibility = View.GONE
        subreddit_tv_error.visibility = View.VISIBLE
    }
}