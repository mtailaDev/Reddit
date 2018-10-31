package com.example.matthewtaila.redditstockx.feed

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matthewtaila.redditstockx.common.network.NetworkServices
import com.example.matthewtaila.redditstockx.feed.model.PostFeed
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RedditFeedViewModel : ViewModel(){

    val postList = MutableLiveData<PostFeed>()

    @SuppressLint("CheckResult")
    fun getPosts(subReddit : String = "", ordering : String = "hot"){

        NetworkServices.getRedditServices().getFeed(ordering)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                // todo log
                postList.value = it
            }, {
                // todo log
                it.printStackTrace()
            })

    }

}