package app.u_business.presentation.ui.sign_in

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import app.u_business.data.network.query.user.LoginBody
import app.u_business.data.network.query.user.RegistrationBody
import app.u_business.data.network.response.user.login.LoginResponse
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

sealed class AuthEventAction {
    data class Success(val loginResponse: LoginResponse?) : AuthEventAction()
    data class Error(val string: String?) : AuthEventAction()
}

data class AuthEvent(val action: AuthEventAction)
class AuthVM(app : Application,
             private val repo: AuthRepository ) : BaseViewModel(app), CoroutineScope by MainScope() {
    val authEvents = MutableLiveData<AuthEvent>()
    private val callback = object : Callback<LoginResponse>{
        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
            if (response.code() == 400){
                authEvents.postValue(AuthEvent(AuthEventAction.Error(response.body()?.message)))
            }
            if (response.isSuccessful && response.body() != null){
                authEvents.postValue(AuthEvent(AuthEventAction.Success(response.body())))
            }
        }
        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            authEvents.postValue(AuthEvent(AuthEventAction.Error(t.message)))
        }
    }

    fun registerUser(registrationBody: RegistrationBody){
        launch (Dispatchers.IO){
             repo.registerNewUser(registrationBody, callback)
        }
    }

    fun signIn(loginBody: LoginBody){
        launch (Dispatchers.IO){
            repo.signIn(loginBody,callback)
        }
    }
}