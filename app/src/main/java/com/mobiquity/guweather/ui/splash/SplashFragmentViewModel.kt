package com.mobiquity.guweather.ui.splash

import android.content.SharedPreferences
import com.mobiquity.guweather.core.BaseViewModel
import javax.inject.Inject

/**
 * Created by Harish on 2021-01-23
 */

class SplashFragmentViewModel @Inject constructor(var sharedPreferences: SharedPreferences) : BaseViewModel() {
    var navigateDashboard = false
}
