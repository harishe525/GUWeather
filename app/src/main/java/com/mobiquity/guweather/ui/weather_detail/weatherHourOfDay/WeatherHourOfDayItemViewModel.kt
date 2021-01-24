package com.mobiquity.guweather.ui.weather_detail.weatherHourOfDay

import androidx.databinding.ObservableField
import com.mobiquity.guweather.core.BaseViewModel
import com.mobiquity.guweather.domain.model.ListItem
import javax.inject.Inject

/**
 * Created by Harish on 2021-011-23
 */

class WeatherHourOfDayItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}
