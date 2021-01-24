package com.mobiquity.guweather.db.entity

import android.os.Parcelable
import androidx.room.*
import com.mobiquity.guweather.domain.model.ForecastResponse
import com.mobiquity.guweather.domain.model.ListItem
import kotlinx.android.parcel.Parcelize

/**
 * Created by Harish on 2021-01-23
 */

@Parcelize
@Entity(tableName = "Forecast")
data class ForecastEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @Embedded
    var city: CityEntity?,

    @ColumnInfo(name = "list")
    var list: List<ListItem>?
) : Parcelable {

    @Ignore
    constructor(forecastResponse: ForecastResponse) : this(
        id = 0,
        city = forecastResponse.city?.let { CityEntity(it) },
        list = forecastResponse.list
    )
}
