package app.u_business.presentation.ui.eventlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.u_business.presentation.ui.base.BaseViewModel
import app.u_business.presentation.ui.eventlist.model.EventList
import app.u_business.presentation.ui.eventlist.repo.eventlist.EventListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class EventListViewModel(app : Application,
                         private val repo: EventListRepository
) : BaseViewModel(app), CoroutineScope by MainScope() {

    private val _state = MutableLiveData<State>(State.Init())
    val state: LiveData<State> get() = _state

    private val _mutableLiveDataEventList = MutableLiveData<List<EventList>>()
    val eventList: LiveData<List<EventList>> get() = _mutableLiveDataEventList

    fun loadeventListFromApi(idUser: String) {
        viewModelScope.launch {
            try {
                    _state.value = State.Loading(null)
                val fetchEventList = repo.fetcheventList(idUser)
                _mutableLiveDataEventList.postValue(fetchEventList)
                _mutableLiveDataEventList.value = fetchEventList
                _state.value = State.Success(fetchEventList)


            } catch (e: Exception) {
                    val message = "Ошибка загрузки данных"
                    _state.value = State.Error(Throwable(message))

                // log error anyway
                Log.e(
                    EventListViewModel::class.java.simpleName,
                    "Error grab event list data from API: ${e.message}"
                )
            }
        }
    }
}