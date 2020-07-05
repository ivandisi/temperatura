package it.iv.temperatura.net.openweather

import android.util.Log
import it.iv.temperatura.net.openweather.api.OpenWeatherApi
import it.iv.temperatura.net.openweather.model.OpenWeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpenWeatherProvider{

    private val URL = "http://api.openweathermap.org/"

    companion object Factory {
        fun create(): OpenWeatherProvider = OpenWeatherProvider()
    }

    fun getData(license: String, result: OpenWeatherInterface) {
        val request = NetDataService.buildService(URL, OpenWeatherApi::class.java)
        val call = request.getWeather(license)

        call.enqueue(object : Callback<OpenWeatherData> {
            override fun onResponse(call: Call<OpenWeatherData>, response: Response<OpenWeatherData>) {
                if (response.isSuccessful){
                    result.onSuccess(response.body())
                } else {
                    result.onError(throw Throwable("Error!"))
                }
            }
            override fun onFailure(call: Call<OpenWeatherData>, t: Throwable) {
                result.onError(t)
            }
        })
    }
}

