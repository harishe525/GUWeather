package com.mobiquity.guweather.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobiquity.guweather.di.ViewModelFactory
import com.mobiquity.guweather.di.key.ViewModelKey
import com.mobiquity.guweather.ui.dashboard.DashboardFragmentViewModel
import com.mobiquity.guweather.ui.dashboard.forecast.ForecastItemViewModel
import com.mobiquity.guweather.ui.main.GUWeatherViewModel
import com.mobiquity.guweather.ui.search.SearchViewModel
import com.mobiquity.guweather.ui.search.result.SearchResultItemViewModel
import com.mobiquity.guweather.ui.splash.SplashFragmentViewModel
import com.mobiquity.guweather.ui.weather_detail.WeatherDetailViewModel
import com.mobiquity.guweather.ui.weather_detail.weatherHourOfDay.WeatherHourOfDayItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Harish on 2021-01-23
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(SplashFragmentViewModel::class)
    abstract fun provideSplashFragmentViewModel(splashFragmentViewModel: SplashFragmentViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(GUWeatherViewModel::class)
    abstract fun provideMainViewModel(GUWeatherViewModel: GUWeatherViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(DashboardFragmentViewModel::class)
    abstract fun provideDashboardFragmentViewModel(dashboardFragmentViewModel: DashboardFragmentViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ForecastItemViewModel::class)
    abstract fun provideForecastItemViewModel(forecastItemViewModel: ForecastItemViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(WeatherDetailViewModel::class)
    abstract fun provideWeatherDetailViewModel(weatherDetailViewModel: WeatherDetailViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(WeatherHourOfDayItemViewModel::class)
    abstract fun provideWeatherHourOfDayItemViewModel(weatherHourOfDayItemViewModel: WeatherHourOfDayItemViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SearchViewModel::class)
    abstract fun provideSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SearchResultItemViewModel::class)
    abstract fun provideSearchResultItemViewModel(searchResultItemViewModel: SearchResultItemViewModel): ViewModel
}
