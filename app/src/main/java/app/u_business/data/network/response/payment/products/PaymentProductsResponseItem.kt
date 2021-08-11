package app.u_business.data.network.response.payment.products


import com.google.gson.annotations.SerializedName

data class PaymentProductsResponseItem(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("items")
    val items: Items?
)