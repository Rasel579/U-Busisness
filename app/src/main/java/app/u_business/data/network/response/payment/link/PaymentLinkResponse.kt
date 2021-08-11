package app.u_business.data.network.response.payment.link

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName

data class PaymentLinkResponse(
    @SerializedName("link")
    val link: String
)