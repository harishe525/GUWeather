package com.mobiquity.guweather.ui.search

import com.mobiquity.guweather.core.BaseViewState
import com.mobiquity.guweather.db.entity.CitiesForSearchEntity
import com.mobiquity.guweather.utils.domain.Status

/**
 * Created by Harish on 2021-01-23
 */

class SearchViewState(
        val status: Status,
        val error: String? = null,
        val data: List<CitiesForSearchEntity>? = null
) : BaseViewState(status, error) {
    fun getSearchResult() = data
}
