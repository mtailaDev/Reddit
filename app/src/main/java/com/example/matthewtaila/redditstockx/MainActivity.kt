package com.example.matthewtaila.redditstockx

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.matthewtaila.redditstockx.databinding.ActivityMainBinding
import com.example.matthewtaila.redditstockx.feed.RedditPostFeedFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityViewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        setOnClickListeners()
        // todo - observeLiveData
    }

    private fun setOnClickListeners() {
        main_iv_subredditSearch.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id){
            R.id.main_iv_subredditSearch ->{
                if (main_et_subredditValue.text.toString().isNotEmpty())
                    mainActivityViewModel.searchForSubreddit(main_et_subredditValue.text.toString())
                else Toast.makeText(this, getString(R.string.search_box_empty_error), Toast.LENGTH_LONG).show()
            }
        }
    }

    fun ObserveLiveData(){
        // todo - observe subReddit live data
        // todo - check if fragment hosting subreddits is at the top of frag stack
        // todo - if it's not, then create a new instance passing in 'it'
        // todo - if it's is, do nothing because the fragment will be observing this value too, and it will refresh list
    }

}
