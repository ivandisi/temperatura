package it.iv.temperatura.user

import it.iv.temperatura.net.netatmo.model.LoginRequest

class NetatmoUser{

    private val USER = "user"
    private val PASSWORD = "password"
    private val CLIENTID = "clientid"
    private val CLIENTSECRET = "clientsecret"

    companion object Factory {
        fun getInstance(): NetatmoUser = NetatmoUser()
    }

    fun getUser(): LoginRequest = LoginRequest("password", USER , PASSWORD , CLIENTID, CLIENTSECRET, "read_homecoach")
}