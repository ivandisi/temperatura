package it.iv.temperatura.net.openweather

import android.util.Log
import com.google.gson.Gson
import it.iv.temperatura.net.netatmo.NetatmoInterface
import it.iv.temperatura.net.netatmo.model.LoginData
import it.iv.temperatura.net.netatmo.model.LoginRequest
import it.iv.temperatura.net.netatmo.model.NetatmoData
import it.iv.temperatura.net.openweather.api.NetatmoApi
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class NetatmoProvider{

    private val URL = "https://api.netatmo.com/"

    companion object Factory {
        fun create(): NetatmoProvider = NetatmoProvider()
    }

    fun getData(login: LoginRequest, result: NetatmoInterface) {
        val request = NetDataService.buildService(URL, NetatmoApi::class.java)

        val call = request.doLogin(login.getGrantType(), login.getUsername(), login.getPassword(), login.getClientId(), login.getClientSecret(), login.getScope())
        call.enqueue(object : Callback<LoginData> {
            override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                if (response.isSuccessful) {
                    val call = request.getData(response.body()?.access_token)
                    call.enqueue(object: Callback<NetatmoData>{
                        override fun onResponse(call: Call<NetatmoData>, response: Response<NetatmoData>) {
                            if (response.isSuccessful) {
                                result.onSuccess(response.body())
                            } else {
                                result.onError(throw Throwable("Error!"))
                            }
                        }
                        override fun onFailure(call: Call<NetatmoData>, t: Throwable) {
                            result.onError(t)
                        }
                    })
                } else {
                    result.onError(throw Throwable("Error!"))
                }
            }
            override fun onFailure(call: Call<LoginData>, t: Throwable) {
                result.onError(t)
            }
        })
    }
}

