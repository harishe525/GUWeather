package com.mobiquity.guweather.repo

import NetworkBoundResource
import androidx.lifecycle.LiveData
import com.mobiquity.guweather.core.Constants.NetworkService.RATE_LIMITER_TYPE
import com.mobiquity.guweather.db.entity.ForecastEntity
import com.mobiquity.guweather.domain.datasource.forecast.ForecastLocalDataSource
import com.mobiquity.guweather.domain.datasource.forecast.ForecastRemoteDataSource
import com.mobiquity.guweather.domain.model.ForecastResponse
import com.mobiquity.guweather.utils.domain.RateLimiter
import com.mobiquity.guweather.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class ForecastRepository @Inject constructor(
        private val forecastRemoteDataSource: ForecastRemoteDataSource,
        private val forecastLocalDataSource: ForecastLocalDataSource
) {

    private val forecastListRateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadForecastByCoord(lat: Double, lon: Double, fetchRequired: Boolean, units: String): LiveData<Resource<ForecastEntity>> {
        return object : NetworkBoundResource<ForecastEntity, ForecastResponse>() {
            override fun saveCallResult(item: ForecastResponse) = forecastLocalDataSource.insertForecast(item)

            override fun shouldFetch(data: ForecastEntity?): Boolean = fetchRequired

            override fun loadFromDb(): LiveData<ForecastEntity> = forecastLocalDataSource.getForecast()

            override fun createCall(): Single<ForecastResponse> = forecastRemoteDataSource.getForecastByGeoCords(lat, lon, units)

            override fun onFetchFailed() = forecastListRateLimit.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }
}
