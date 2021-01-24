package com.mobiquity.guweather.ui.search.result

import androidx.databinding.ObservableField
import com.mobiquity.guweather.core.BaseViewModel
import com.mobiquity.guweather.db.entity.CitiesForSearchEntity
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class SearchResultItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<CitiesForSearchEntity>()
}
