package com.mobiquity.guweather.ui.weather_detail

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobiquity.guweather.core.BaseViewModel
import com.mobiquity.guweather.db.entity.ForecastEntity
import com.mobiquity.guweather.domain.datasource.forecast.ForecastLocalDataSource
import com.mobiquity.guweather.domain.model.ListItem
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class WeatherDetailViewModel @Inject constructor(private val forecastLocalDataSource: ForecastLocalDataSource) : BaseViewModel() {

    var weatherItem: ObservableField<ListItem> = ObservableField()
    private var forecastLiveData: LiveData<ForecastEntity> = MutableLiveData()
    var selectedDayDate: String? = null
    var selectedDayForecastLiveData: MutableLiveData<List<ListItem>> = MutableLiveData()

    fun getForecastLiveData() = forecastLiveData

    fun getForecast(): LiveData<ForecastEntity> {
        return forecastLocalDataSource.getForecast()
    }
}
