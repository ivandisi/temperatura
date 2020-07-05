package it.iv.temperatura.net

import it.iv.temperatura.model.TemperatureData


interface DataProviderInterface {
    fun onSuccess(result: TemperatureData?)
}