package app.u_business.data.network.api

import app.u_business.data.network.response.payment.link.PaymentLinkResponse
import app.u_business.data.network.response.payment.products.PaymentProductsResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface PaymentApi {

    @GET("wp-admin/admin-ajax.php")
    suspend fun getProduct(
        @Query("action") action: String = "get_orders",
        @Query("u-member-id") memberId: String
    ): List<PaymentProductsResponseItem>

    @GET("wp-admin/admin-ajax.php")
    suspend fun getPaymentLink(
        @Query("action") action: String = "get_link",
        @Query("u-member-id") memberId: String
    ): PaymentLinkResponse
}