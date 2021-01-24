package com.mobiquity.guweather.domain.datasource.forecast

import com.mobiquity.guweather.db.dao.ForecastDao
import com.mobiquity.guweather.db.entity.ForecastEntity
import com.mobiquity.guweather.domain.model.ForecastResponse
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class ForecastLocalDataSource @Inject constructor(private val forecastDao: ForecastDao) {

    fun getForecast() = forecastDao.getForecast()

    fun insertForecast(forecast: ForecastResponse) = forecastDao.deleteAndInsert(ForecastEntity(forecast))
}
