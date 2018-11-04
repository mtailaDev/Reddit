package com.example.matthewtaila.redditstockx

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.matthewtaila.redditstockx.DetailedPost.DetailedPostFragment
import com.example.matthewtaila.redditstockx.databinding.ActivityMainBinding
import com.example.matthewtaila.redditstockx.feed.RedditPostFeedFragment
import com.example.matthewtaila.redditstockx.subreddits.SubredditsResultFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){


    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityViewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        setOnClickListeners()
        showFeedFragment()
        observeLiveData()
    }

    private fun showFeedFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragmentContainer, RedditPostFeedFragment.newInstance()).commit()
    }

    private fun setOnClickListeners() {
        main_iv_subredditSearch.setOnClickListener{
            if (main_et_subredditValue.text.toString().isNotEmpty())
                mainActivityViewModel.handleSubRedditSearchValue(main_et_subredditValue.text.toString())
            else Toast.makeText(this, getString(R.string.search_box_empty_error), Toast.LENGTH_LONG).show()
        }
    }

    private fun observeLiveData() {
        mainActivityViewModel.subReddit.observe(this, Observer {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fragmentContainer, SubredditsResultFragment.newInstance())
                .commit()
        })
        mainActivityViewModel.selectedSubreddit.observe(this, Observer {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fragmentContainer, RedditPostFeedFragment.newInstance())
                .commit()
        })
        mainActivityViewModel.selectedURL.observe(this, Observer {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fragmentContainer, DetailedPostFragment.newInstance())
                .commit()
        })

    }


}
