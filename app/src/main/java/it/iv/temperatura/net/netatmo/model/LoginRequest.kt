package it.iv.temperatura.net.netatmo.model

import com.google.gson.annotations.SerializedName
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody

data class LoginRequest(
    var grant_type: String,
    var username: String,
    var password: String,
    var client_id: String,
    var client_secret: String,
    var scope: String
){
    override fun toString(): String = grant_type + " " + username + " " + password + " " + client_id +" " + client_secret + " " + scope
    fun getGrantType() : RequestBody = RequestBody.Companion.create("text/plain".toMediaType(), grant_type)
    fun getUsername() : RequestBody = RequestBody.Companion.create("text/plain".toMediaType(), username)
    fun getPassword() : RequestBody = RequestBody.Companion.create("text/plain".toMediaType(), password)
    fun getClientId() : RequestBody = RequestBody.Companion.create("text/plain".toMediaType(), client_id)
    fun getClientSecret() : RequestBody = RequestBody.Companion.create("text/plain".toMediaType(), client_secret)
    fun getScope() : RequestBody = RequestBody.Companion.create("text/plain".toMediaType(), scope)
}
