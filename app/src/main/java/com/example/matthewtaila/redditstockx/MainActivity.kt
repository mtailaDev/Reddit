package com.example.matthewtaila.redditstockx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.matthewtaila.redditstockx.databinding.ActivityMainBinding
import com.example.matthewtaila.redditstockx.feed.RedditPostFeedFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        showFeedFragment()
    }

    private fun showFeedFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.main_fl_fragmentContainer, RedditPostFeedFragment.newInstance()).commit()
    }
}
