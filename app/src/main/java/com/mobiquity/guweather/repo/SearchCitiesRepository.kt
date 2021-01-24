package com.mobiquity.guweather.repo

import NetworkBoundResource
import androidx.lifecycle.LiveData
import com.mobiquity.guweather.core.Constants.NetworkService.RATE_LIMITER_TYPE
import com.mobiquity.guweather.db.entity.CitiesForSearchEntity
import com.mobiquity.guweather.domain.datasource.searchCities.SearchCitiesLocalDataSource
import com.mobiquity.guweather.domain.datasource.searchCities.SearchCitiesRemoteDataSource
import com.mobiquity.guweather.domain.model.SearchResponse
import com.mobiquity.guweather.utils.domain.RateLimiter
import com.mobiquity.guweather.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class SearchCitiesRepository @Inject constructor(
        private val searchCitiesLocalDataSource: SearchCitiesLocalDataSource,
        private val searchCitiesRemoteDataSource: SearchCitiesRemoteDataSource
) {

    private val rateLimiter = RateLimiter<String>(1, TimeUnit.SECONDS)

    fun loadCitiesByCityName(cityName: String?): LiveData<Resource<List<CitiesForSearchEntity>>> {
        return object : NetworkBoundResource<List<CitiesForSearchEntity>, SearchResponse>() {
            override fun saveCallResult(item: SearchResponse) = searchCitiesLocalDataSource.insertCities(item)

            override fun shouldFetch(data: List<CitiesForSearchEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<CitiesForSearchEntity>> = searchCitiesLocalDataSource.getCityByName(cityName)

            override fun createCall(): Single<SearchResponse> = searchCitiesRemoteDataSource.getCityWithQuery(
                cityName
                    ?: ""
            )

            override fun onFetchFailed() = rateLimiter.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }
}
