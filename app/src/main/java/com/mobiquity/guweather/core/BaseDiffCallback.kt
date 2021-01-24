package com.mobiquity.guweather.core

import androidx.recyclerview.widget.DiffUtil


/**
 * Created by Harish on 2021-01-23
 */

open class BaseDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
