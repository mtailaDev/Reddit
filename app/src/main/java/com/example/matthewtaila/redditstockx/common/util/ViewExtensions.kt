package com.example.matthewtaila.redditstockx.common.util

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import com.example.matthewtaila.redditstockx.R

fun Group.setAllOnClickListener(listener: View.OnClickListener?) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}

fun Group.resetOrderingTextViews() {
    referencedIds.forEach { id ->
        val orderingTextView : TextView = rootView.findViewById(id)
        orderingTextView.setTextColor(ContextCompat.getColor(this.context, R.color.ordering_active))
    }
}

fun Group.inactiveOrderingTextView() {
    referencedIds.forEach { id ->
        val orderingTextView : TextView = rootView.findViewById(id)
        orderingTextView.setTextColor(ContextCompat.getColor(this.context, R.color.ordering_inactive))
    }
}
