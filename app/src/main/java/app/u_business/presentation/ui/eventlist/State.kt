package app.u_business.presentation.ui.eventlist

import app.u_business.presentation.ui.eventlist.model.EventList

sealed class State{
    class Init : State()
    data class Success(val serverResponseData: List<EventList>) : State()
    data class Error(val error: Throwable) : State()
    data class Loading(val progress: Int?) : State()
}
