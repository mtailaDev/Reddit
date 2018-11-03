package com.example.matthewtaila.redditstockx.common.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.matthewtaila.redditstockx.R

@BindingAdapter("showThumbnail", "thumbnail")
fun ImageView.showThumbnail(control: Int?, url: String?) {
    if (control == null){
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
        Glide.with(this.context).load(url).into(this)
    }
}

@BindingAdapter("postScore")
fun TextView.postScore(score: Int?) {
    this.text = "$score"
}

@BindingAdapter("appendSubreddit")
fun TextView.appendSubreddit(sub: String?) {
    this.text = "r/"+sub
}

@BindingAdapter("subredditTitle")
fun TextView.subRedditTitle(title: String?) {
    this.text = title
}

@BindingAdapter("subredditSubscribers")
fun TextView.subredditSubscribers(subscribers: Int?) {
    this.text = "Subscribers: $subscribers"
}

@BindingAdapter("icon")
fun ImageView.subredditIcon(url: String?) {
    if (url!!.isEmpty()){
        Glide.with(this.context).load(R.drawable.reddit_stub).apply(RequestOptions.circleCropTransform()).into(this)
    } else {
        Glide.with(this.context).load(url).apply(RequestOptions.circleCropTransform()).into(this)
    }
}

