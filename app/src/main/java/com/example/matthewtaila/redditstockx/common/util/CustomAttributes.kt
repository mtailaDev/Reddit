package com.example.matthewtaila.redditstockx.common.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.matthewtaila.redditstockx.R

@BindingAdapter("app:glideSrc", "app:placeholder", "app:circle", requireAll = false)
fun ImageView.glideSrc(src: String?, placeholder: Int?, circle: Boolean = false) {
    Glide.with(this)
        .load(src)
        .apply {
            placeholder?.let { apply(RequestOptions().placeholder(R.drawable.reddit_stub)) }
            if (circle) apply(RequestOptions.circleCropTransform())
        }
        .into(this)
}

