package it.iv.temperatura.net.netatmo.model

import com.google.gson.annotations.SerializedName

data class NetatmoData(
    @SerializedName("status")
    val status: String,
    @SerializedName("time_server")
    val time_server: Int,
    @SerializedName("body")
    val body: Body
){
    override fun toString(): String = status + " " + time_server
    fun getSensorData(): DeviceData = body?.getDevice()?.dashboard_data
}

data class Body(
    @SerializedName("devices")
    val devices: List<Device>,
    @SerializedName("user")
    val user: User
) {
   fun getDevice():Device = devices?.get(0)
}

data class User(
    @SerializedName("mail")
    val mail: String
)

data class Device(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("station_name")
    val station_name: String,
    @SerializedName("date_setup")
    val date_setup: String,
    @SerializedName("last_setup")
    val last_setup: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("last_status_store")
    val last_status_store: String,
    @SerializedName("module_name")
    val module_name: String,
    @SerializedName("firmware")
    val firmware: Int,
    @SerializedName("wifi_status")
    val wifi_status: Int,
    @SerializedName("reachable")
    val reachable: Boolean,
    @SerializedName("co2_calibrating")
    val co2_calibrating: Boolean,
    @SerializedName("dashboard_data")
    val dashboard_data: DeviceData
)

data class DeviceData(
    @SerializedName("Temperature")
    val temperature: Double,
    @SerializedName("Humidity")
    val humidity: Double,
    @SerializedName("Noise")
    val noise: Double,
    @SerializedName("Pressure")
    val pressure: Double,
    @SerializedName("AbsolutePressure")
    val absolutePressure: Double,
    @SerializedName("CO2")
    val CO2: Double
){
    override fun toString(): String = "" + temperature + " " + humidity + " " + noise + " " + pressure + " " + absolutePressure + " " + CO2
}