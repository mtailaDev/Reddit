package com.example.matthewtaila.redditstockx

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.matthewtaila.redditstockx.DetailedPost.DetailedPostFragment
import com.example.matthewtaila.redditstockx.common.util.inactiveOrderingTextView
import com.example.matthewtaila.redditstockx.common.util.resetOrderingTextViews
import com.example.matthewtaila.redditstockx.common.util.setAllOnClickListener
import com.example.matthewtaila.redditstockx.databinding.ActivityMainBinding
import com.example.matthewtaila.redditstockx.feed.RedditPostFeedFragment
import com.example.matthewtaila.redditstockx.subreddits.SubredditsResultFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityViewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        mainActivityViewModel.activateOrdering()
        mainActivityViewModel.order.value = MainActivityViewModel.Ordering.Hot.order
        setOnClickListeners()
        showFeedFragment()
        observeLiveData()
    }

    private fun showFeedFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.main_fragmentContainer, RedditPostFeedFragment.newInstance()).commit()
    }

    override fun onResume() {
        super.onResume()
        activateOrderingGroupListener()
    }

    private fun inactivateOrderingGroupListener() {
        main_ordering_group.setAllOnClickListener(null)
    }

    private fun activateOrderingGroupListener() {
        mainActivityViewModel.orderingActive.value?.let {
                main_ordering_group.setAllOnClickListener(View.OnClickListener {
                    main_ordering_group.resetOrderingTextViews()
                    when (it.id){
                        R.id.main_tv_orderHot ->{
                            main_tv_orderHot.setTextColor(ContextCompat.getColor(this, R.color.ordering_selected))
                            mainActivityViewModel.order.value = MainActivityViewModel.Ordering.Hot.order
                        }
                        R.id.main_tv_orderTop ->{
                            main_tv_orderTop.setTextColor(ContextCompat.getColor(this, R.color.ordering_selected))
                            mainActivityViewModel.order.value = MainActivityViewModel.Ordering.Top.order
                        }
                        R.id.main_tv_orderNew ->{
                            main_tv_orderNew.setTextColor(ContextCompat.getColor(this, R.color.ordering_selected))
                            mainActivityViewModel.order.value = MainActivityViewModel.Ordering.New.order
                        }
                        R.id.main_tv_orderControversial ->{
                            main_tv_orderControversial.setTextColor(ContextCompat.getColor(this, R.color.ordering_selected))
                            mainActivityViewModel.order.value = MainActivityViewModel.Ordering.Controversial.order
                        }
                        R.id.main_tv_orderRising ->{
                            main_tv_orderRising.setTextColor(ContextCompat.getColor(this, R.color.ordering_selected))
                            mainActivityViewModel.order.value = MainActivityViewModel.Ordering.Rising.order
                        }
                    }
                })
        }
    }

    private fun setOnClickListeners() {
        main_iv_subredditSearch.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id){
            R.id.main_iv_subredditSearch ->{
                if (main_et_subredditValue.text.toString().isNotEmpty())
                    mainActivityViewModel.handleSubRedditSearchValue(main_et_subredditValue.text.toString())
                else Toast.makeText(this, getString(R.string.search_box_empty_error), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeLiveData(){
        mainActivityViewModel.subReddit.observe(this, Observer {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fragmentContainer, SubredditsResultFragment.newInstance())
                .commit()
        })
        mainActivityViewModel.selectedSubreddit.observe(this, Observer {
            mainActivityViewModel.order.value = MainActivityViewModel.Ordering.Hot.order
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
        mainActivityViewModel.orderingActive.observe(this, Observer {
            if (it) {
                main_ordering_group.resetOrderingTextViews()
                activateOrderingGroupListener()
                resetToOrderHot()
            } else {
                main_ordering_group.inactiveOrderingTextView()
                inactivateOrderingGroupListener()
            }
        })
    }

    private fun resetToOrderHot() {
        main_tv_orderHot.setTextColor(ContextCompat.getColor(this, R.color.ordering_selected))
    }


}
