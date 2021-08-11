package app.u_business.data.network.response.payment.products


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("date_paid")
    val datePaid: DatePaid?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("parent_id")
    val parentId: Int?,
    @SerializedName("status")
    val status: String?
)