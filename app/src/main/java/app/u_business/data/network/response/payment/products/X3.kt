package app.u_business.data.network.response.payment.products


import com.google.gson.annotations.SerializedName

data class X3(
    @SerializedName("name")
    val name: String?,
    @SerializedName("order_id")
    val orderId: Int?,
    @SerializedName("product_id")
    val productId: Int?,
    @SerializedName("subtotal")
    val subtotal: String?,
    @SerializedName("subtotal_tax")
    val subtotalTax: String?,
    @SerializedName("total")
    val total: String?
)