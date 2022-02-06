package app.u_business.data.repo.auth

import app.u_business.data.network.query.user.LoginBody
import app.u_business.data.network.query.user.RegistrationBody
import app.u_business.data.network.response.payment.link.PaymentLinkResponse
import app.u_business.data.network.response.payment.products.PaymentProductsResponseItem
import app.u_business.data.network.response.user.login.LoginResponse
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.presentation.utils.SharedPreferencesHelper
import com.google.gson.Gson
//TODO(Изменить на получение данных с сервера)
class AuthRepositoryImpl(private val prefs: SharedPreferencesHelper) : AuthRepository {

    override fun isFirstOpen(): Boolean = prefs.isFirstOpen

    override suspend fun registerNewUser(user: RegistrationBody): LoginResponse =
        AuthMockData.createLoginResponse()

    override suspend fun signIn(user: LoginBody): LoginResponse =
        AuthMockData.createLoginResponse()

    override suspend fun recovery(email: String) = "BackEndRepo.api.recovery(email)"
    override fun getAccessToken() = prefs.accessToken
    override fun getUser() = Gson().fromJson(prefs.registrationBody, LoginResponse::class.java)
    override fun saveUser(user: LoginResponse) {
        prefs.registrationBody = Gson().toJson(user)
    }

    override fun saveToken(accessToken: String?) {
        prefs.accessToken = accessToken
    }

    override fun makeAuthed(boolean: Boolean) {
        prefs.isAuthed = boolean
    }

    override suspend fun getProducts(id: String): List<PaymentProductsResponseItem> =
        AuthMockData.createPaymentProducts()

    override suspend fun getLink(id: String): PaymentLinkResponse =
        AuthMockData.createPaymentLinkResponse()

    override fun isAuthed(): Boolean = prefs.isAuthed

}