package it.iv.temperatura.user

class OpenWeatherUser {

    private val TOKEN = "token";
    private val CITY = "torino"

    companion object Factory {
        fun getInstance(): OpenWeatherUser = OpenWeatherUser()
    }

    fun getToken(): String = TOKEN
    fun getCity(): String = CITY
}