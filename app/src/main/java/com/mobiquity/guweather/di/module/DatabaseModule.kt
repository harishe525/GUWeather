package com.mobiquity.guweather.di.module

import android.content.Context
import androidx.room.Room
import com.mobiquity.guweather.db.WeatherDatabase
import com.mobiquity.guweather.db.dao.CitiesForSearchDao
import com.mobiquity.guweather.db.dao.CurrentWeatherDao
import com.mobiquity.guweather.db.dao.ForecastDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java, "WeatherApp-DB"
        ).build()
    }

    @Singleton
    @Provides
    fun provideForecastDao(db: WeatherDatabase): ForecastDao {
        return db.forecastDao()
    }

    @Singleton
    @Provides
    fun provideCurrentWeatherDao(db: WeatherDatabase): CurrentWeatherDao {
        return db.currentWeatherDao()
    }

    @Singleton
    @Provides
    fun provideCitiesForSearchDao(db: WeatherDatabase): CitiesForSearchDao {
        return db.citiesForSearchDao()
    }
}
