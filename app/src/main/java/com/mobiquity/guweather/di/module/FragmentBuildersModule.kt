package com.mobiquity.guweather.di.module

import com.mobiquity.guweather.ui.dashboard.DashboardFragment
import com.mobiquity.guweather.ui.search.SearchFragment
import com.mobiquity.guweather.ui.splash.SplashFragment
import com.mobiquity.guweather.ui.weather_detail.WeatherDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Harish on 2021-01-23
 */

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun contributeWeatherDetailFragment(): WeatherDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment
}
