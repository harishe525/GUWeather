package com.mobiquity.guweather

import android.os.Build
import com.faskn.app.weatherapp.viewModel.SearchViewModelTest
import com.mobiquity.guweather.dao.CitiesForSearchDaoTest
import com.mobiquity.guweather.dao.CurrentWeatherDaoTest
import com.mobiquity.guweather.dao.ForecastDaoTest
import com.mobiquity.guweather.repo.CurrentWeatherRepositoryTest
import com.mobiquity.guweather.repo.ForecastRepositoryTest
import com.mobiquity.guweather.viewModel.DashboardViewModelTest
import com.mobiquity.guweather.viewModel.WeatherDetailViewModelTest
import com.mobiquity.guweather.viewState.CurrentWeatherViewStateTest
import com.mobiquity.guweather.viewState.ForecastViewStateTest
import com.mobiquity.guweather.viewState.SearchViewStateTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.robolectric.annotation.Config

/**
 * Created by Harish on 2021-01-2
 */

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(Suite::class)
@Suite.SuiteClasses(
    CitiesForSearchDaoTest::class,
    CurrentWeatherDaoTest::class,
    CurrentWeatherViewStateTest::class,
    DashboardViewModelTest::class,
    ForecastDaoTest::class,
    ForecastViewStateTest::class,
    SearchViewStateTest::class,
    SearchViewModelTest::class,
    WeatherDetailViewModelTest::class,
    ForecastRepositoryTest::class,
    CurrentWeatherRepositoryTest::class
)
class TestSuite
