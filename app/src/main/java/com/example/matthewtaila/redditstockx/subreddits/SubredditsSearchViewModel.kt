package com.example.matthewtaila.redditstockx.subreddits

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matthewtaila.redditstockx.common.network.IRedditAPI
import com.example.matthewtaila.redditstockx.subreddits.model.SubredditSearchResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SubredditsSearchViewModel : ViewModel(), KoinComponent {

    private val redditApiServices: IRedditAPI by inject()

    val subredditResults = MutableLiveData<SubredditSearchResult>()

    @SuppressLint("CheckResult")
    fun searchSubreddits(searchTerm: String?) {

        redditApiServices.searchSubReddits(searchTerm!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                subredditResults.value = it
            }, {
                it.printStackTrace()
            })
    }
}