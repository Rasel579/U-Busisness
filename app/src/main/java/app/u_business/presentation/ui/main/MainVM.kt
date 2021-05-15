package app.u_business.presentation.ui.main

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.presentation.ui.base.BaseViewModel
import app.u_business.presentation.utils.SharedPreferencesHelper
import org.koin.core.inject

enum class NavigateEventAction {
    ONBOARDING,
    AUTH,
    HOME
}

data class NavigateEvent(val action: NavigateEventAction, val extras: Bundle = Bundle.EMPTY)


class MainVM(
    app: Application,
    repo: AuthRepository
) : BaseViewModel(app) {
    val navEvents = MutableLiveData<NavigateEvent>()

    init {
        if (repo.isFirstOpen())
            navEvents.postValue(NavigateEvent(NavigateEventAction.ONBOARDING))
        else {
            if (repo.isAuthed())
                navEvents.postValue(NavigateEvent(NavigateEventAction.HOME))
            else
                navEvents.postValue(NavigateEvent(NavigateEventAction.AUTH))
        }
    }
}