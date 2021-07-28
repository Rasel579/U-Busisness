package app.u_business.data.repo.auth

import app.u_business.data.network.api.BackEndRepo
import app.u_business.data.network.query.user.LoginBody
import app.u_business.data.network.query.user.RegistrationBody
import app.u_business.data.network.response.user.login.LoginResponse
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.presentation.utils.SharedPreferencesHelper
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import retrofit2.Callback

class AuthRepositoryImpl(private val prefs: SharedPreferencesHelper) : AuthRepository {

    override fun isFirstOpen(): Boolean = prefs.isFirstOpen

    override suspend fun registerNewUser(user: RegistrationBody): LoginResponse =
        BackEndRepo.api.registerUser(user)

    override suspend fun signIn(user: LoginBody): LoginResponse =
        BackEndRepo.api.loginUser(user, RequestBody.create("images".toMediaType(), "jpg"))

    override fun isAuthed(): Boolean = prefs.isAuthed

}