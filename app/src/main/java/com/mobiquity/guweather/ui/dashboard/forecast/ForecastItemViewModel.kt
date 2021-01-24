package com.mobiquity.guweather.ui.dashboard.forecast

import androidx.databinding.ObservableField
import com.mobiquity.guweather.core.BaseViewModel
import com.mobiquity.guweather.domain.model.ListItem
import java.util.*
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class ForecastItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}
