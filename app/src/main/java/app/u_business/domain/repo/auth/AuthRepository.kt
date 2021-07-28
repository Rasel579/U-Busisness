package app.u_business.domain.repo.auth

import app.u_business.data.network.query.user.LoginBody
import app.u_business.data.network.query.user.RegistrationBody
import app.u_business.data.network.response.user.login.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Callback

interface AuthRepository {
    fun isFirstOpen(): Boolean
    fun isAuthed(): Boolean
<<<<<<< HEAD
    fun registerNewUser(user: RegistrationBody, callback: Callback<LoginResponse>)
    fun signIn(user: LoginBody, callback: Callback<LoginResponse>)
=======

>>>>>>> master
}