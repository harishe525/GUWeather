package com.mobiquity.guweather.domain.datasource.searchCities

import androidx.lifecycle.LiveData
import com.mobiquity.guweather.db.dao.CitiesForSearchDao
import com.mobiquity.guweather.db.entity.CitiesForSearchEntity
import com.mobiquity.guweather.domain.model.SearchResponse
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class SearchCitiesLocalDataSource @Inject constructor(private val citiesForSearchDao: CitiesForSearchDao) {

    fun getCityByName(cityName: String?): LiveData<List<CitiesForSearchEntity>> = citiesForSearchDao.getCityByName(cityName)

    fun insertCities(response: SearchResponse) {
        response.hits
            ?.map { CitiesForSearchEntity(it) }
            ?.let { citiesForSearchDao.insertCities(it) }
    }
}
