package app.u_business.presentation.ui.sign_in

import android.app.Application
import androidx.lifecycle.MutableLiveData
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.presentation.ui.base.BaseViewModel

enum class AuthEventAction {
    SUCCESS,
    ERORR
}

data class AuthEvent(val action: AuthEventAction)
class AuthVM(app : Application, repo : AuthRepository ) : BaseViewModel(app) {
    val authEvents = MutableLiveData<AuthEvent>()

}