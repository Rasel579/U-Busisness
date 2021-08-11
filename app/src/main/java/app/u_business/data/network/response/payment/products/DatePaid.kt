package app.u_business.data.network.response.payment.products


import com.google.gson.annotations.SerializedName

data class DatePaid(
    @SerializedName("date")
    val date: String?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("timezone_type")
    val timezoneType: Int?
)