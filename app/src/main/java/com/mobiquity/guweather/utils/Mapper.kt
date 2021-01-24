package com.mobiquity.guweather.utils

/**
 * Created by Harish on 2021-01-23
 */

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}
