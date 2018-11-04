package com.example.matthewtaila.redditstockx.feed

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matthewtaila.redditstockx.common.network.IRedditAPI
import com.example.matthewtaila.redditstockx.feed.model.PostFeed
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class RedditFeedViewModel : ViewModel(), KoinComponent{

    private val redditApiServices : IRedditAPI by inject()

    val postList = MutableLiveData<PostFeed>()

    @SuppressLint("CheckResult")
    fun getPosts(subReddit : String = "", ordering : String = "hot"){

        redditApiServices.getFeed(ordering)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postList.value = it
            }, {
                it.printStackTrace()
            })

    }

    @SuppressLint("CheckResult")
    fun getSubPosts(subReddit : String = "", ordering : String = "hot"){
        redditApiServices.getSubFeed(subReddit, ordering)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postList.value = it
            }, {
                it.printStackTrace()
            })

    }

}