package com.mobiquity.guweather.domain.datasource.currentWeather

import com.mobiquity.guweather.domain.WeatherAppAPI
import com.mobiquity.guweather.domain.model.CurrentWeatherResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class CurrentWeatherRemoteDataSource @Inject constructor(private val api: WeatherAppAPI) {

    fun getCurrentWeatherByGeoCords(lat: Double, lon: Double, units: String): Single<CurrentWeatherResponse> = api.getCurrentByGeoCords(lat, lon, units)
}
