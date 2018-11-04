package com.example.matthewtaila.redditstockx

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matthewtaila.redditstockx.common.network.IRedditAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MainActivityViewModel : ViewModel(), KoinComponent{

    val subReddit = MutableLiveData<String>()
    val selectedURL = MutableLiveData<String>()
    val selectedSubreddit = MutableLiveData<String>()

    fun handleSelectedPost(url : String){
        selectedURL.value = url
    }

    fun handleSubRedditSearchValue(searchTerm: String) {
        subReddit.value = searchTerm
    }

    fun selectSubreddit(sub : String){
        selectedSubreddit.value = sub
    }
}