package com.mobiquity.guweather.ui.dashboard

import android.transition.TransitionInflater
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiquity.guweather.R
import com.mobiquity.guweather.core.BaseFragment
import com.mobiquity.guweather.core.Constants
import com.mobiquity.guweather.databinding.FragmentDashboardBinding
import com.mobiquity.guweather.di.Injectable
import com.mobiquity.guweather.domain.model.ListItem
import com.mobiquity.guweather.domain.usecase.CurrentWeatherUseCase
import com.mobiquity.guweather.domain.usecase.ForecastUseCase
import com.mobiquity.guweather.ui.dashboard.forecast.ForecastAdapter
import com.mobiquity.guweather.ui.main.GUWeatherActivity
import com.mobiquity.guweather.utils.extensions.isNetworkAvailable
import com.mobiquity.guweather.utils.extensions.observeWith

class DashboardFragment : BaseFragment<DashboardFragmentViewModel, FragmentDashboardBinding>(R.layout.fragment_dashboard, DashboardFragmentViewModel::class.java), Injectable {

    override fun init() {
        super.init()
        initForecastAdapter()
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val lat: String? = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LAT, "")
        val lon: String? = binding.viewModel?.sharedPreferences?.getString(Constants.Coords.LON, "")

        if (lat?.isNotEmpty() == true && lon?.isNotEmpty() == true) {
            binding.viewModel?.setCurrentWeatherParams(CurrentWeatherUseCase.CurrentWeatherParams(lat, lon, isNetworkAvailable(requireContext()), Constants.Coords.METRIC))
            binding.viewModel?.setForecastParams(ForecastUseCase.ForecastParams(lat, lon, isNetworkAvailable(requireContext()), Constants.Coords.METRIC))
        }

        binding.viewModel?.getForecastViewState()?.observeWith(
            viewLifecycleOwner
        ) {
            with(binding) {
                viewState = it
                it.data?.list?.let { forecasts -> initForecast(forecasts) }
                (activity as GUWeatherActivity).viewModel.toolbarTitle.set(it.data?.city?.getCityAndCountry())
            }
        }

        binding.viewModel?.getCurrentWeatherViewState()?.observeWith(
            viewLifecycleOwner
        ) {
            with(binding) {
                containerForecast.viewState = it
            }
        }
    }

    private fun initForecastAdapter() {
        val adapter = ForecastAdapter { item, cardView, forecastIcon, dayOfWeek, temp, tempMaxMin ->
            val action = DashboardFragmentDirections.actionDashboardFragmentToWeatherDetailFragment(item)
            findNavController()
                .navigate(
                    action,
                    FragmentNavigator.Extras.Builder()
                        .addSharedElements(
                            mapOf(
                                cardView to cardView.transitionName,
                                forecastIcon to forecastIcon.transitionName,
                                dayOfWeek to dayOfWeek.transitionName,
                                temp to temp.transitionName,
                                tempMaxMin to tempMaxMin.transitionName
                            )
                        )
                        .build()
                )
        }

        binding.recyclerForecast.adapter = adapter
        binding.recyclerForecast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        postponeEnterTransition()
        binding.recyclerForecast.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    private fun initForecast(list: List<ListItem>) {
        (binding.recyclerForecast.adapter as ForecastAdapter).submitList(list)
    }
}
