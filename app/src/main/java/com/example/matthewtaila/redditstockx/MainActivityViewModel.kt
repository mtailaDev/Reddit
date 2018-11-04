package com.example.matthewtaila.redditstockx

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.standalone.KoinComponent

class MainActivityViewModel : ViewModel(), KoinComponent {

    val subReddit = MutableLiveData<String>()
    val selectedURL = MutableLiveData<String>()
    val selectedSubreddit = MutableLiveData<String>()

    fun selectDetailedPost(url: String) {
        selectedURL.value = url
    }

    fun handleSubRedditSearchValue(searchTerm: String) {
        subReddit.value = searchTerm
    }

    fun selectSubreddit(sub: String) {
        selectedSubreddit.value = sub
    }
}