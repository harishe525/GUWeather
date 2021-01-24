package com.mobiquity.guweather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobiquity.guweather.db.dao.CitiesForSearchDao
import com.mobiquity.guweather.db.dao.CurrentWeatherDao
import com.mobiquity.guweather.db.dao.ForecastDao
import com.mobiquity.guweather.db.entity.CitiesForSearchEntity
import com.mobiquity.guweather.db.entity.CurrentWeatherEntity
import com.mobiquity.guweather.db.entity.ForecastEntity
import com.mobiquity.guweather.utils.typeconverters.DataConverter


@Database(
    entities = [
        ForecastEntity::class,
        CurrentWeatherEntity::class,
        CitiesForSearchEntity::class
    ],
    version = 2
)
@TypeConverters(DataConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao

    abstract fun currentWeatherDao(): CurrentWeatherDao

    abstract fun citiesForSearchDao(): CitiesForSearchDao
}
