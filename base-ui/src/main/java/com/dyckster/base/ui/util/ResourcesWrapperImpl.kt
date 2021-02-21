package com.dyckster.base.ui.util

import android.content.res.Resources

class ResourcesWrapperImpl
constructor(
    private val resources: Resources
) : ResourcesWrapper {

    override fun getString(res: Int): String {
        return resources.getString(res)
    }
}