package it.iv.temperatura.net.netatmo

import it.iv.temperatura.net.netatmo.model.NetatmoData

interface NetatmoInterface {
    fun onSuccess(result: NetatmoData?)
    fun onError(error: Throwable)
}