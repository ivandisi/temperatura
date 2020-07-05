package it.iv.temperatura.user

class OpenWeatherUser {

    private val TOKEN = "token";

    companion object Factory {
        fun getInstance(): OpenWeatherUser = OpenWeatherUser()
    }

    fun getToken(): String = TOKEN
}