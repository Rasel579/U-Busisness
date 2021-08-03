package app.u_business.data.repo.auth

import app.u_business.data.network.api.BackEndRepo
import app.u_business.data.network.query.user.LoginBody
import app.u_business.data.network.query.user.RegistrationBody
import app.u_business.data.network.response.user.login.LoginResponse
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.presentation.utils.SharedPreferencesHelper
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import retrofit2.Callback

class AuthRepositoryImpl(private val prefs: SharedPreferencesHelper) : AuthRepository {

    override fun isFirstOpen(): Boolean = prefs.isFirstOpen

    override suspend fun registerNewUser(user: RegistrationBody): LoginResponse =
        BackEndRepo.api.registerUser(user)

    override suspend fun signIn(user: LoginBody): LoginResponse =
        BackEndRepo.api.loginUser(user)

    override suspend fun recovery(email: String) = BackEndRepo.api.recovery(email)
    override fun getAccessToken() = prefs.accessToken
    override fun getUser() = Gson().fromJson(prefs.registrationBody, LoginResponse::class.java)
    override fun saveUser(user: LoginResponse) {
      prefs.registrationBody = Gson().toJson(user)
    }

    override fun saveToken(accessToken: String?) {
        prefs.accessToken = accessToken
    }

    override fun makeAuthed(boolean: Boolean) {
        prefs.isAuthed =  boolean
    }


    override fun isAuthed(): Boolean = prefs.isAuthed

}