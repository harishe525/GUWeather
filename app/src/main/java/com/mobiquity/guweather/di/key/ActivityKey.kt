package com.mobiquity.guweather.di.key

import android.app.Activity
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Harish on 2021-01-23
 */

@MapKey
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class ActivityKey(val value: KClass<out Activity>)
