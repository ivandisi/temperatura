package it.iv.temperatura.net.openweather.api

import it.iv.temperatura.net.netatmo.model.LoginData
import it.iv.temperatura.net.netatmo.model.LoginRequest
import it.iv.temperatura.net.netatmo.model.NetatmoData
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface NetatmoApi {
    @Multipart
    @POST("oauth2/token")
    fun doLogin(@Part("grant_type") grant_type: RequestBody,
                @Part("username") username: RequestBody,
                @Part ("password") password: RequestBody,
                @Part ("client_id") client_id: RequestBody,
                @Part ("client_secret") client_secret: RequestBody,
                @Part ("scope") scope: RequestBody): Call<LoginData>

    @GET("api/gethomecoachsdata")
    fun getData(@Query("access_token") accessToken: String?): Call<NetatmoData>
}

