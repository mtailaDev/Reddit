package com.example.matthewtaila.redditstockx.feed

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matthewtaila.redditstockx.MainActivityViewModel
import com.example.matthewtaila.redditstockx.R
import com.example.matthewtaila.redditstockx.common.util.setAllOnClickListener
import com.example.matthewtaila.redditstockx.databinding.FragmentRedditPostFeedBinding
import com.example.matthewtaila.redditstockx.feed.model.Ordering
import com.example.matthewtaila.redditstockx.feed.model.PostFeed
import com.example.matthewtaila.redditstockx.feed.model.PostFeedAdapter
import kotlinx.android.synthetic.main.fragment_reddit_post_feed.*

class RedditPostFeedFragment : Fragment() {

    private lateinit var mBinding: FragmentRedditPostFeedBinding
    private lateinit var redditPostViewModel: RedditFeedViewModel
    private lateinit var mainActivityViewModel: MainActivityViewModel

    companion object {
        @JvmStatic
        fun newInstance(): RedditPostFeedFragment {
            return RedditPostFeedFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        redditPostViewModel = ViewModelProviders.of(this)[RedditFeedViewModel::class.java]
        mainActivityViewModel = ViewModelProviders.of(activity!!)[MainActivityViewModel::class.java]
        redditPostViewModel.order.value = Ordering.Hot.order
        observeLiveData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_reddit_post_feed, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoadingState()
        setOrderingTabClickListener()
    }

    private fun setOrderingTabClickListener() {
        feed_ordering_group.setAllOnClickListener(View.OnClickListener {
                            feed_ordering_group.resetOrderingTextViews()
                when (it.id) {
                    R.id.feed_tv_orderHot -> {
                        feed_tv_orderHot.setTextColor(ContextCompat.getColor(requireContext(), R.color.ordering_selected))
                        redditPostViewModel.order.value = Ordering.Hot.order
                    }
                    R.id.feed_tv_orderTop -> {
                        feed_tv_orderTop.setTextColor(ContextCompat.getColor(requireContext(), R.color.ordering_selected))
                        redditPostViewModel.order.value = Ordering.Top.order
                    }
                    R.id.feed_tv_orderNew -> {
                        feed_tv_orderNew.setTextColor(ContextCompat.getColor(requireContext(), R.color.ordering_selected))
                        redditPostViewModel.order.value = Ordering.New.order
                    }
                    R.id.feed_tv_orderControversial -> {
                        feed_tv_orderControversial.setTextColor(ContextCompat.getColor(requireContext(), R.color.ordering_selected))
                        redditPostViewModel.order.value = Ordering.Controversial.order
                    }
                    R.id.feed_tv_orderRising -> {
                        feed_tv_orderRising.setTextColor(ContextCompat.getColor(requireContext(), R.color.ordering_selected))
                        redditPostViewModel.order.value = Ordering.Rising.order
                    }
                }
            })
    }

    override fun onResume() {
        super.onResume()
        // todo - change location
        mainActivityViewModel.subReddit.value?.let {
            redditPostViewModel.getSubPosts(
                mainActivityViewModel.selectedSubreddit.value!!,
                redditPostViewModel.order.value!!
            )
        } ?: kotlin.run {
            redditPostViewModel.getPosts()
        }
    }

    private fun showLoadingState() {
        feed_lav_loading.visibility = View.VISIBLE
        feed_rv_posts.visibility = View.GONE
    }

    private fun hideLoadingState() {
        val animatorSet = AnimatorSet()
        val hideLottieAnimation = ObjectAnimator.ofFloat(feed_lav_loading, View.ALPHA, 1f, 0f)
        val showRVAnimation = ObjectAnimator.ofFloat(feed_rv_posts, View.ALPHA, 0f, 1f)
        animatorSet.playTogether(hideLottieAnimation, showRVAnimation)
        animatorSet.interpolator = FastOutSlowInInterpolator()
        animatorSet.duration = 200
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                feed_lav_loading?.let { it.visibility = View.GONE }
            }

            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)

                feed_rv_posts.let { it.visibility = View.VISIBLE }
            }
        })
        animatorSet.start()
    }

    fun observeLiveData() {
        redditPostViewModel.postList.observe(this, Observer {
            if (it.postDataList.isNotEmpty()) {
                hideLoadingState()
                setupRecyclerView(it)
            }
        })
        redditPostViewModel.order.observe(this, Observer {
            mainActivityViewModel.subReddit.value?.let {
                redditPostViewModel.getSubPosts(
                    mainActivityViewModel.selectedSubreddit.value!!,
                    redditPostViewModel.order.value!!
                )
            }
        })
    }

    private fun setupRecyclerView(postFeed: PostFeed) {
        val mAdapter = PostFeedAdapter(postFeed, mainActivityViewModel)
        feed_rv_posts.adapter = mAdapter
        feed_rv_posts.layoutManager = LinearLayoutManager(context)
    }


    fun Group.resetOrderingTextViews() {
        referencedIds.forEach { id ->
            val orderingTextView: TextView = rootView.findViewById(id)
            orderingTextView.setTextColor(ContextCompat.getColor(this.context, R.color.ordering_active))
        }
    }

}