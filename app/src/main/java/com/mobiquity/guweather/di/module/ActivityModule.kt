package com.mobiquity.guweather.di.module

import com.mobiquity.guweather.di.scope.PerActivity
import com.mobiquity.guweather.ui.main.GUWeatherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Harish on 2021-01-23
 */

@Module
abstract class ActivityModule {

    /**
     * Injects [GUWeatherActivity]
     *
     * @return an instance of [GUWeatherActivity]
     */

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun mainActivity(): GUWeatherActivity
}
