package it.iv.temperatura.net.netatmo.model

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("access_token")
    val access_token: String
){
    override fun toString(): String = access_token
    fun getAssesToken(): String = access_token
}