package com.mobiquity.guweather.repo

import NetworkBoundResource
import androidx.lifecycle.LiveData
import com.mobiquity.guweather.core.Constants.NetworkService.RATE_LIMITER_TYPE
import com.mobiquity.guweather.db.entity.CurrentWeatherEntity
import com.mobiquity.guweather.domain.datasource.currentWeather.CurrentWeatherLocalDataSource
import com.mobiquity.guweather.domain.datasource.currentWeather.CurrentWeatherRemoteDataSource
import com.mobiquity.guweather.domain.model.CurrentWeatherResponse
import com.mobiquity.guweather.utils.domain.RateLimiter
import com.mobiquity.guweather.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class CurrentWeatherRepository @Inject constructor(
        private val currentWeatherRemoteDataSource: CurrentWeatherRemoteDataSource,
        private val currentWeatherLocalDataSource: CurrentWeatherLocalDataSource
) {

    private val currentWeatherRateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadCurrentWeatherByGeoCords(lat: Double, lon: Double, fetchRequired: Boolean, units: String): LiveData<Resource<CurrentWeatherEntity>> {
        return object : NetworkBoundResource<CurrentWeatherEntity, CurrentWeatherResponse>() {
            override fun saveCallResult(item: CurrentWeatherResponse) = currentWeatherLocalDataSource.insertCurrentWeather(item)

            override fun shouldFetch(data: CurrentWeatherEntity?): Boolean = fetchRequired

            override fun loadFromDb(): LiveData<CurrentWeatherEntity> = currentWeatherLocalDataSource.getCurrentWeather()

            override fun createCall(): Single<CurrentWeatherResponse> = currentWeatherRemoteDataSource.getCurrentWeatherByGeoCords(lat, lon, units)

            override fun onFetchFailed() = currentWeatherRateLimit.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }
}
