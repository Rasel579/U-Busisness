package app.u_business.presentation.ui.main

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.presentation.ui.base.BaseViewModel
import app.u_business.presentation.utils.SharedPreferencesHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class NavigateEventAction {
    ONBOARDING,
    AUTH,
    HOME,
    WAIT_PAYMENT
}

data class NavigateEvent(val action: NavigateEventAction, val extras: Bundle = Bundle.EMPTY)


class MainVM(
    app: Application,
    private val repo: AuthRepository,
    private val prefs: SharedPreferencesHelper
) : BaseViewModel(app) {
    val navEvents = MutableLiveData<NavigateEvent>()

    init {

        viewModelScope.launch(Dispatchers.Main) {
            if (repo.isFirstOpen())
                navEvents.postValue(NavigateEvent(NavigateEventAction.ONBOARDING))
            else {
                if (repo.isAuthed()) {
                    if (prefs.userId != null) {
                        val res = repo.getProducts(prefs.userId!!)
                        if (res.isNotEmpty() && res.first().data?.status == "completed")
                            navEvents.postValue(NavigateEvent(NavigateEventAction.HOME))
                        else
                            navEvents.postValue(NavigateEvent(NavigateEventAction.WAIT_PAYMENT))
                    } else
                        navEvents.postValue(NavigateEvent(NavigateEventAction.AUTH))
                } else
                    navEvents.postValue(NavigateEvent(NavigateEventAction.AUTH))
            }
        }
    }
}
