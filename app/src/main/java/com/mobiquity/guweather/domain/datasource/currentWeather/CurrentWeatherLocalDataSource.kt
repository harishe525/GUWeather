package com.mobiquity.guweather.domain.datasource.currentWeather

import com.mobiquity.guweather.db.dao.CurrentWeatherDao
import com.mobiquity.guweather.db.entity.CurrentWeatherEntity
import com.mobiquity.guweather.domain.model.CurrentWeatherResponse
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class CurrentWeatherLocalDataSource @Inject constructor(private val currentWeatherDao: CurrentWeatherDao) {

    fun getCurrentWeather() = currentWeatherDao.getCurrentWeather()

    fun insertCurrentWeather(currentWeather: CurrentWeatherResponse) = currentWeatherDao.deleteAndInsert(CurrentWeatherEntity(currentWeather))
}
