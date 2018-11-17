package com.example.matthewtaila.redditstockx

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matthewtaila.redditstockx.feed.model.Post
import org.koin.standalone.KoinComponent

class MainActivityViewModel : ViewModel(), KoinComponent {

    val subReddit = MutableLiveData<String>()
    val selectedURL = MutableLiveData<Post>()
    val selectedSubreddit = MutableLiveData<String>()

    fun handleSubRedditSearchValue(searchTerm: String) {
        subReddit.value = searchTerm
    }

    fun selectSubreddit(sub: String) {
        selectedSubreddit.value = sub
    }
}