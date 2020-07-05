package it.iv.temperatura.net.openweather

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetDataService {
    private fun buildRetrofit(url: String) : Retrofit{
        var logging = HttpLoggingInterceptor()
        logging.apply {
            //logging.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun<T> buildService(url: String, service: Class<T>): T{
        return buildRetrofit(url).create(service)
    }
}