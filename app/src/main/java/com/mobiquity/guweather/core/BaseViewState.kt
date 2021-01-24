package com.mobiquity.guweather.core

import com.mobiquity.guweather.utils.domain.Status


/**
 * Created by Harish on 2021-01-23
 */

open class BaseViewState(val baseStatus: Status, val baseError: String?) {
    fun isLoading() = baseStatus == Status.LOADING
    fun getErrorMessage() = baseError
    fun shouldShowErrorMessage() = baseError != null
}
