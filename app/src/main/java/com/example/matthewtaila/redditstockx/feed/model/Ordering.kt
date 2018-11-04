package com.example.matthewtaila.redditstockx.feed.model

sealed class Ordering(val order: String) {
    object Hot : Ordering("hot")
    object Top : Ordering("top")
    object Controversial : Ordering("controversial")
    object Rising : Ordering("rising")
    object New : Ordering("new")
}