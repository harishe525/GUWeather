package com.mobiquity.guweather.viewModel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.mobiquity.guweather.db.WeatherDatabase
import com.mobiquity.guweather.db.dao.ForecastDao
import com.mobiquity.guweather.domain.datasource.forecast.ForecastLocalDataSource
import com.mobiquity.guweather.ui.weather_detail.WeatherDetailViewModel
import com.mobiquity.guweather.util.createSampleForecastResponse
import com.mobiquity.guweather.util.getOrAwaitValue
import io.mockk.MockKAnnotations
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by Harish on 2021-01-24
 */
@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class WeatherDetailViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var weatherDetailViewModel: WeatherDetailViewModel
    private lateinit var weatherDatabase: WeatherDatabase
    private lateinit var forecastDao: ForecastDao
    private lateinit var forecastLocalDataSource: ForecastLocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        weatherDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        forecastDao = weatherDatabase.forecastDao()
        forecastLocalDataSource = ForecastLocalDataSource(forecastDao)
        weatherDetailViewModel = WeatherDetailViewModel(forecastLocalDataSource)
    }

    @After
    fun closeDatabase() {
        weatherDatabase.close()
    }

    @Test
    fun `insert forecast and when getForecast called the livedata result must be ForecastEntity`() {
        // When
        forecastLocalDataSource.insertForecast(createSampleForecastResponse())

        // Then
        val result = weatherDetailViewModel.getForecast().getOrAwaitValue()
        Truth.assertThat(result.city?.cityName).isEqualTo("Istanbul")
    }
}
