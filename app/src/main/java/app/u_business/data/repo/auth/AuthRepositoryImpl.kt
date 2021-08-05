package app.u_business.data.repo.auth

import app.u_business.data.network.api.BackEndRepo
import app.u_business.data.network.query.user.LoginBody
import app.u_business.data.network.query.user.RegistrationBody
import app.u_business.data.network.response.user.login.LoginResponse
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.presentation.utils.SharedPreferencesHelper
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Callback
import java.io.File

class AuthRepositoryImpl(private val prefs: SharedPreferencesHelper): AuthRepository{

    override fun isFirstOpen(): Boolean = prefs.isFirstOpen
    override fun isAuthed(): Boolean =  prefs.isAuthed
    override fun registerNewUser(user: RegistrationBody, callback: Callback<LoginResponse>){
        BackEndRepo.api.registerUser(user).enqueue(callback)
    }

    override fun signIn(user: LoginBody, callback: Callback<LoginResponse>) {
        BackEndRepo.api.loginUser(user, RequestBody.create(MediaType.get("images"),"jpg")).enqueue(callback)
    }
}