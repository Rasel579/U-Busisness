package app.u_business.presentation.ui.sign_in

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
    data class SuccessRecovery(val message : String) : AuthEventAction()
}

data class AuthEvent(val action: AuthEventAction)
class AuthVM(
    app: Application,
    private val repo: AuthRepository
) : BaseViewModel(app) {

    val authEvents = MutableLiveData<AuthEvent>()

    fun registerUser(registrationBody: RegistrationBody) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = repo.registerNewUser(registrationBody)
                Log.d(Companion.TAG, "registerUser: $res")
                authEvents.postValue(AuthEvent(AuthEventAction.Success(res)))
            }catch (exception : Exception){
                authEvents.postValue(AuthEvent(AuthEventAction.Error(exception.message)))
            }
        }
    }

    fun signIn(loginBody: LoginBody) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = repo.signIn(loginBody)
                authEvents.postValue(AuthEvent(AuthEventAction.Success(res)))
            } catch (exception : Exception){
                authEvents.postValue(AuthEvent(AuthEventAction.Error(exception.message)))
            }
        }
    }

    fun recovery(email: String){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val res = repo.recovery(email)
                authEvents.postValue(AuthEvent((AuthEventAction.SuccessRecovery(res))))
            } catch (exception : Exception){
                authEvents.postValue(AuthEvent(AuthEventAction.Error(exception.message)))
            }
        }
    }

    companion object {
        private const val TAG = "AuthVM"
    }
}