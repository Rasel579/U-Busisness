package app.u_business.presentation.ui.eventlist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import app.u_business.domain.repo.EventsRepository
import app.u_business.presentation.ui.base.BaseViewModel
import app.u_business.presentation.ui.eventlist.response.EventItem
import app.u_business.presentation.ui.news.ResponseState
import app.u_business.presentation.ui.news.getData

class EventListViewModel(app: Application, private val repo: EventsRepository) :
    BaseViewModel(app) {
    val ldEvents: MutableLiveData<ResponseState<List<EventItem>>> = MutableLiveData()

    fun requestEventList() = getData(ldEvents) { repo.getEventList() }
    fun requestSearchNews(word: String) = getData(ldEvents) { repo.searchEvents(word) }
}
