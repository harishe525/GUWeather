package com.mobiquity.guweather.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by Harish on 2021-01-21
 */

inline fun <T : Any> LiveData<T>.observeWith(
    lifecycleOwner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
) {
    observe(
        lifecycleOwner,
        Observer {
            it ?: return@Observer
            onChanged.invoke(it)
        }
    )
}
