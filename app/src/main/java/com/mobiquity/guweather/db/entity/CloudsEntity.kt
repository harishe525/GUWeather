package com.mobiquity.guweather.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.mobiquity.guweather.domain.model.Clouds
import kotlinx.android.parcel.Parcelize

/**
 * Created by Harish on 2021-01-23
 */

@Parcelize
@Entity(tableName = "Clouds")
data class CloudsEntity(
    @ColumnInfo(name = "all")
    var all: Int
) : Parcelable {
    @Ignore
    constructor(clouds: Clouds?) : this(
        all = clouds?.all ?: 0
    )
}
