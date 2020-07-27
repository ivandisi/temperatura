package it.iv.temperatura.model

import it.id.unitoreader.UnitoData
import it.iv.temperatura.net.netatmo.model.NetatmoData
import it.iv.temperatura.net.openweather.model.OpenWeatherData

data class TemperatureData(
    private var internal: NetatmoData?,
    private var external: OpenWeatherData?,
    private var externalUnito: UnitoData?
){
    fun getInternal():NetatmoData? = internal
    fun getExternal():OpenWeatherData? = external
    fun getExternalUnito():UnitoData? = externalUnito
}