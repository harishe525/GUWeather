package com.mobiquity.guweather.ui.dashboard

import com.mobiquity.guweather.core.BaseViewState
import com.mobiquity.guweather.db.entity.CurrentWeatherEntity
import com.mobiquity.guweather.utils.domain.Status


/**
 * Created by Harish on 2021-01-23
 */

class CurrentWeatherViewState(
        val status: Status,
        val error: String? = null,
        val data: CurrentWeatherEntity? = null
) : BaseViewState(status, error) {
    fun getForecast() = data
}
