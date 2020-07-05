package it.iv.temperatura.net.openweather

import it.iv.temperatura.net.openweather.model.OpenWeatherData

interface OpenWeatherInterface{
    fun onSuccess(result: OpenWeatherData?)
    fun onError(error: Throwable)
}