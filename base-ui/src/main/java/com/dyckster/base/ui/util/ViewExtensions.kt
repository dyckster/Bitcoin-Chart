@file:Suppress("UNCHECKED_CAST")

package com.dyckster.base.ui.util

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.dyckster.base.ui.R

fun <T> Activity.bindView(@IdRes res: Int): Lazy<T> = lazy { findViewById<View>(res) as T }

fun <T> RecyclerView.ViewHolder.bindView(@IdRes res: Int): Lazy<T> =
    lazy { itemView.findViewById<View>(res) as T }

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

@JvmOverloads
fun Toolbar.withBackButton(
    activity: Activity?,
    iconRes: Int = R.drawable.ic_round_arrow_back_24,
    block: () -> Unit = { activity?.onBackPressed() }
) = apply {
    setNavigationIcon(iconRes)
    setNavigationOnClickListener { block.invoke() }
    setNavigationContentDescription(R.string.cd_back)
}