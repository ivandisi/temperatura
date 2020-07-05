package it.iv.temperatura.net.openweather.api

import it.iv.temperatura.net.openweather.model.OpenWeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherApi {
    @GET("data/2.5/weather?q=torino,it&units=metric")
    fun getWeather(@Query("APPID") key: String): Call<OpenWeatherData>
}

