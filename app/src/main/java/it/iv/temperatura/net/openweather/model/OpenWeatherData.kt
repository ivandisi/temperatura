package it.iv.temperatura.net.openweather.model

import com.google.gson.annotations.SerializedName

data class OpenWeatherData(
    @SerializedName("name")
    val name: String,
    @SerializedName("main")
    val main: OpenWeather,
    @SerializedName("wind")
    val wind: Wind

){
    override fun toString(): String = main.temp + " " + main.humidity + " " + main.pressure
    fun getSensorData(): OpenWeather = main
    fun getSensorDataWind(): Wind = wind
}
data class Wind(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val deg: Double
)

data class OpenWeather(
    @SerializedName("temp")
    val temp: String,
    @SerializedName("feels_like")
    val feels_like: String,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("humidity")
    val humidity: Double
)