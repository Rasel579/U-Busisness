package app.u_business.data.repo.auth

import app.u_business.data.network.response.payment.link.PaymentLinkResponse
import app.u_business.data.network.response.payment.products.*
import app.u_business.data.network.response.user.login.LoginResponse

object AuthMockData {
    fun createLoginResponse() = LoginResponse(
        message = "Ok",
        userId = 1,
        role = "payed",
        accessToken = "1234",
        refreshToken = "1234"
    )

    fun createPaymentProducts(): List<PaymentProductsResponseItem> = mutableListOf<PaymentProductsResponseItem>().apply {
        add(
            PaymentProductsResponseItem(
            data = Data(
                currency = "RUB",
                datePaid = DatePaid(
                    date = "12/01/2012",
                    timezone = "EU",
                    timezoneType = 12
                ),
                id = 1,
                parentId = 1,
                status = "completed"
            ),
                items = Items(
                    x3 = X3(
                        name = "string",
                        orderId = 1,
                        productId = 1,
                        subtotal = "12",
                        subtotalTax = "1",
                        total = "1212"
                    )
                )
        )
        )
    }

    fun createPaymentLinkResponse() = PaymentLinkResponse(
        link = "link"
    )

}