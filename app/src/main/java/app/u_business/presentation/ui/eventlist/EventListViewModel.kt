package app.u_business.presentation.ui.eventlist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import app.u_business.presentation.ui.base.BaseViewModel
import app.u_business.presentation.ui.eventlist.repo.eventlist.EventListRepository
import app.u_business.presentation.ui.eventlist.response.EventItem
import app.u_business.presentation.ui.news.ResponseState
import app.u_business.presentation.ui.news.getData


/*sealed class EventListState{
    data class Success(val serverResponseData: List<EventItem?>?) : EventListState()
    data class Error(val error: Throwable) : EventListState()
    object Loading : EventListState()
}*/

class EventListViewModel(app: Application, private val repo: EventListRepository) :
    BaseViewModel(app) {
    val ldEvents: MutableLiveData<ResponseState<List<EventItem>>> = MutableLiveData()

    fun requestEventList() = getData(ldEvents) { repo.getEventList() }
}

/*class EventListViewModel(    app: Application,    private val repo: EventListRepository
) : BaseViewModel(app), CoroutineScope by MainScope() {

    val state = MutableLiveData<EventListState>()

    init {
        getEventList()
    }

    private fun getEventList() {
        viewModelScope.launch {
            try {
                val res = repo.getEventList()

                state.postValue(EventListState.Success(res))
            } catch (e: Exception) {
                val message = "Ошибка загрузки данных"
                state.postValue(EventListState.Error(e))
                // log error anyway
                Log.e(
                    EventListViewModel::class.java.simpleName,
                    "Error grab event list data from API: ${e.message}"
                )
            }
        }
    }
}*/