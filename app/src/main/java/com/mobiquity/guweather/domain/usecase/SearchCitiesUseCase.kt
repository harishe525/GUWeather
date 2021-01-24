package com.mobiquity.guweather.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.mobiquity.guweather.db.entity.CitiesForSearchEntity
import com.mobiquity.guweather.repo.SearchCitiesRepository
import com.mobiquity.guweather.ui.search.SearchViewState
import com.mobiquity.guweather.utils.UseCaseLiveData
import com.mobiquity.guweather.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class SearchCitiesUseCase @Inject internal constructor(private val repository: SearchCitiesRepository) : UseCaseLiveData<SearchViewState, SearchCitiesUseCase.SearchCitiesParams, SearchCitiesRepository>() {

    override fun getRepository(): SearchCitiesRepository = repository

    override fun buildUseCaseObservable(params: SearchCitiesParams?): LiveData<SearchViewState> {
        return repository.loadCitiesByCityName(
            cityName = params?.city ?: ""
        ).map {
            onSearchResultReady(it)
        }
    }

    private fun onSearchResultReady(resource: Resource<List<CitiesForSearchEntity>>): SearchViewState {
        return SearchViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class SearchCitiesParams(
        val city: String = ""
    ) : Params()
}
