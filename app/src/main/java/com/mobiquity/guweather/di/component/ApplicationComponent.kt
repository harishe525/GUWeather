package com.mobiquity.guweather.di.component

import android.app.Application
import com.mobiquity.guweather.WeatherApp
import com.mobiquity.guweather.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetModule::class,
        DatabaseModule::class,
        ActivityModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(weatherApp: WeatherApp)
}
