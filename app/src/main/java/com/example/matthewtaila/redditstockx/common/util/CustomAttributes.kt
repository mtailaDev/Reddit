package com.example.matthewtaila.redditstockx.common.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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