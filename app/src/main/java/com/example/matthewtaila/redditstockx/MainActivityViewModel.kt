package com.example.matthewtaila.redditstockx

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel(){

    val subReddit = MutableLiveData<String>()
    val selectedURL = MutableLiveData<String>()

    fun handleSelectedPost(url : String){
        selectedURL.value = url
    }

    fun searchForSubreddit(searchTerm: String) {
        Log.i("Search Term", searchTerm)
        // todo - subReddit.val = searchTerm

    }
}