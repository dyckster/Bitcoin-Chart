package com.dyckster.utils

import org.mockito.Mockito

internal fun <T> anyObject(): T {
    return Mockito.anyObject<T>()
}