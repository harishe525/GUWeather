package com.mobiquity.guweather.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.mobiquity.guweather.core.Constants
import com.mobiquity.guweather.db.entity.ForecastEntity
import com.mobiquity.guweather.repo.ForecastRepository
import com.mobiquity.guweather.ui.dashboard.ForecastMapper
import com.mobiquity.guweather.ui.dashboard.ForecastViewState
import com.mobiquity.guweather.utils.UseCaseLiveData
import com.mobiquity.guweather.utils.domain.Resource
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class ForecastUseCase @Inject internal constructor(private val repository: ForecastRepository) : UseCaseLiveData<ForecastViewState, ForecastUseCase.ForecastParams, ForecastRepository>() {

    override fun getRepository(): ForecastRepository {
        return repository
    }

    override fun buildUseCaseObservable(params: ForecastParams?): LiveData<ForecastViewState> {
        return repository.loadForecastByCoord(
            params?.lat?.toDouble() ?: 0.0,
            params?.lon?.toDouble() ?: 0.0,
            params?.fetchRequired
                ?: false,
            units = params?.units ?: Constants.Coords.METRIC
        ).map {
            onForecastResultReady(it)
        }
    }

    private fun onForecastResultReady(resource: Resource<ForecastEntity>): ForecastViewState {
        val mappedList = resource.data?.list?.let { ForecastMapper().mapFrom(it) }
        resource.data?.list = mappedList

        return ForecastViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class ForecastParams(
        val lat: String = "",
        val lon: String = "",
        val fetchRequired: Boolean,
        val units: String
    ) : Params()
}
