package app.u_business.presentation.ui.sign_in

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.u_business.data.network.query.user.LoginBody
import app.u_business.data.network.query.user.RegistrationBody
import app.u_business.data.network.response.payment.products.PaymentProductsResponseItem
import app.u_business.data.network.response.user.login.LoginResponse
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.presentation.ui.base.BaseViewModel
import app.u_business.presentation.utils.SharedPreferencesHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class AuthEventAction {
    data class Success(
        val loginResponse: LoginResponse?,
        val resPayment: List<PaymentProductsResponseItem>?
    ) : AuthEventAction()

    data class Link(val link: String) : AuthEventAction()
    data class Payed(
        val loginResponse: LoginResponse?
    ) : AuthEventAction()

    data class Error(val string: String?) : AuthEventAction()
    data class SuccessRecovery(val message: String) : AuthEventAction()
    data class NotPayed(val loginResponse: LoginResponse?) : AuthEventAction()
}

data class AuthEvent(val action: AuthEventAction)
class AuthVM(
    app: Application,
    private val repo: AuthRepository,
    private val prefs: SharedPreferencesHelper
) : BaseViewModel(app) {

    val authEvents = MutableLiveData<AuthEvent>()

    fun registerUser(registrationBody: RegistrationBody) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = repo.registerNewUser(registrationBody)
                Log.d(TAG, "registerUser: $res")
                authEvents.postValue(AuthEvent(AuthEventAction.Success(res, null)))
            } catch (exception: Exception) {
                authEvents.postValue(AuthEvent(AuthEventAction.Error(exception.message)))
            }
        }
    }

    fun getPaymentLink() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                prefs.userId?.let {
                    val res = repo.getLink(it)
                    authEvents.postValue(AuthEvent(AuthEventAction.Link(res.link)))
                }
            } catch (exception: Exception) {
                authEvents.postValue(AuthEvent(AuthEventAction.Error(exception.message)))
            }
        }
    }

    fun signIn(loginBody: LoginBody) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = repo.signIn(loginBody)
                val resPayment = repo.getProducts(prefs.userId!!)
                Log.d(TAG, "signIn: ${res} $resPayment")
                if (resPayment.isNotEmpty() && resPayment.first().data?.status == "completed")
                    authEvents.postValue(AuthEvent(AuthEventAction.Payed(res)))
                else
                    authEvents.postValue(AuthEvent(AuthEventAction.NotPayed(res)))
            } catch (exception: Exception) {
                Log.d(TAG, "signIn: $exception")
                authEvents.postValue(AuthEvent(AuthEventAction.Error(exception.message)))
            }
        }
    }

    fun recovery(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = repo.recovery(email)
                authEvents.postValue(AuthEvent((AuthEventAction.SuccessRecovery(res))))
            } catch (exception: Exception) {
                authEvents.postValue(AuthEvent(AuthEventAction.Error(exception.message)))
            }
        }
    }

    companion object {
        private const val TAG = "AuthVM"
    }
}