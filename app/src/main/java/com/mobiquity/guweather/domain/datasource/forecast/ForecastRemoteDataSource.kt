package com.mobiquity.guweather.domain.datasource.forecast

import com.mobiquity.guweather.domain.WeatherAppAPI
import com.mobiquity.guweather.domain.model.ForecastResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class ForecastRemoteDataSource @Inject constructor(private val api: WeatherAppAPI) {

    fun getForecastByGeoCords(lat: Double, lon: Double, units: String): Single<ForecastResponse> = api.getForecastByGeoCords(lat, lon, units)
}
