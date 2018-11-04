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
    val orderingActive = MutableLiveData<Boolean>()
    val order = MutableLiveData<String>()

    fun selectDetailedPost(url : String){
        selectedURL.value = url
    }

    fun handleSubRedditSearchValue(searchTerm: String) {
        subReddit.value = searchTerm
    }

    fun selectSubreddit(sub : String){
        selectedSubreddit.value = sub
    }

    fun inactiveOrdering(){
        orderingActive.value = false
    }

    fun activateOrdering(){
        orderingActive.value = true
    }

    sealed class Ordering(val order: String){
        object Hot : Ordering("hot")
        object Top : Ordering("top")
        object Controversial : Ordering("controversial")
        object Rising : Ordering("rising")
        object New : Ordering("new")
    }
}