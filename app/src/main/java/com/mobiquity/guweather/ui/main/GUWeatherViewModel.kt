package com.mobiquity.guweather.ui.main

import androidx.databinding.ObservableField
import com.mobiquity.guweather.core.BaseViewModel
import javax.inject.Inject

class GUWeatherViewModel @Inject internal constructor() : BaseViewModel() {
    var toolbarTitle: ObservableField<String> = ObservableField()
}
