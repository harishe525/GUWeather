package com.mobiquity.guweather.ui.weather_detail

import android.transition.TransitionInflater
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mobiquity.guweather.R
import com.mobiquity.guweather.core.BaseFragment
import com.mobiquity.guweather.databinding.FragmentWeatherDetailBinding
import com.mobiquity.guweather.di.Injectable
import com.mobiquity.guweather.domain.model.ListItem
import com.mobiquity.guweather.ui.weather_detail.weatherHourOfDay.WeatherHourOfDayAdapter
import com.mobiquity.guweather.utils.extensions.observeWith
import io.reactivex.disposables.CompositeDisposable

class WeatherDetailFragment : BaseFragment<WeatherDetailViewModel, FragmentWeatherDetailBinding>(R.layout.fragment_weather_detail, WeatherDetailViewModel::class.java), Injectable {

    private val weatherDetailFragmentArgs: WeatherDetailFragmentArgs by navArgs()
    var disposable = CompositeDisposable()

    override fun init() {
        super.init()
        binding.viewModel?.weatherItem?.set(weatherDetailFragmentArgs.weatherItem)
        binding.viewModel?.selectedDayDate = weatherDetailFragmentArgs.weatherItem.dtTxt?.substringBefore(" ")

        binding.viewModel?.getForecast()?.observeWith(viewLifecycleOwner) {
            binding.viewModel?.selectedDayForecastLiveData
                ?.postValue(
                    it.list?.filter { item ->
                        item.dtTxt?.substringBefore(" ") == binding.viewModel?.selectedDayDate
                    }
                )
        }

        binding.viewModel?.selectedDayForecastLiveData?.observeWith(
            viewLifecycleOwner
        ) {
            initWeatherHourOfDayAdapter(it)
        }

        binding.fabClose.setOnClickListener {
            findNavController().popBackStack()
        }

        val inflateTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = inflateTransition
    }

    private fun initWeatherHourOfDayAdapter(list: List<ListItem>) {
        val adapter = WeatherHourOfDayAdapter { item ->
            // TODO - onClick
        }

        binding.recyclerViewHourOfDay.adapter = adapter
        (binding.recyclerViewHourOfDay.adapter as WeatherHourOfDayAdapter).submitList(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
