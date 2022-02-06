package app.u_business.domain.repo.auth

import app.u_business.data.network.query.user.LoginBody
import app.u_business.data.network.query.user.RegistrationBody
import app.u_business.data.network.response.payment.link.PaymentLinkResponse
import app.u_business.data.network.response.payment.products.PaymentProductsResponseItem
import app.u_business.data.network.response.user.login.LoginResponse
import retrofit2.Callback

interface AuthRepository {
    fun isFirstOpen(): Boolean
    fun isAuthed(): Boolean
    suspend fun registerNewUser(user: RegistrationBody): LoginResponse
    suspend fun signIn(user: LoginBody): LoginResponse
    suspend fun recovery(email: String): String
    fun getAccessToken(): String?
    fun getUser(): LoginResponse
    fun saveUser(user : LoginResponse)
    fun saveToken(accessToken : String?)
    fun makeAuthed(boolean: Boolean)
    suspend fun getProducts(id: String): List<PaymentProductsResponseItem>
    suspend fun getLink(id: String): PaymentLinkResponse
}