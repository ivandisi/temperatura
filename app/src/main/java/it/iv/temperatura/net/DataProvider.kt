package it.iv.temperatura.net

import it.iv.temperatura.model.TemperatureData
import it.iv.temperatura.net.netatmo.NetatmoInterface
import it.iv.temperatura.net.netatmo.model.NetatmoData
import it.iv.temperatura.net.openweather.NetatmoProvider
import it.iv.temperatura.net.openweather.OpenWeatherInterface
import it.iv.temperatura.net.openweather.OpenWeatherProvider
import it.iv.temperatura.net.openweather.model.OpenWeatherData
import it.iv.temperatura.user.NetatmoUser
import it.iv.temperatura.user.OpenWeatherUser

object DataProvider {

    var internal: NetatmoData? = null
    var external: OpenWeatherData? = null

    fun runRequest(callback: DataProviderInterface) {
        OpenWeatherProvider.create().getData(OpenWeatherUser.getInstance().getToken(), object:
            OpenWeatherInterface {
            override fun onSuccess(result: OpenWeatherData?){
                external = result
                checkIfReady(callback)

            }
            override fun onError(error: Throwable){
                error.printStackTrace()
            }
        })

        NetatmoProvider.create().getData(NetatmoUser.getInstance().getUser(), object: NetatmoInterface {
            override fun onSuccess(result: NetatmoData?){
                internal = result
                checkIfReady(callback)
            }
            override fun onError(error: Throwable){
                error.printStackTrace()
            }
        })
    }

    fun checkIfReady(result: DataProviderInterface){
        if (internal != null && external != null) {
            val fullData = TemperatureData(internal, external)
            result.onSuccess(fullData)
        }
    }
}