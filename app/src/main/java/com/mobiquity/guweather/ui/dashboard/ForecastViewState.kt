package com.mobiquity.guweather.ui.dashboard

import com.mobiquity.guweather.core.BaseViewState
import com.mobiquity.guweather.db.entity.ForecastEntity
import com.mobiquity.guweather.utils.domain.Status


/**
 * Created by Harish on 2021-03-23
 */

class ForecastViewState(
        val status: Status,
        val error: String? = null,
        val data: ForecastEntity? = null
) : BaseViewState(status, error) {
    fun getForecast() = data
}
