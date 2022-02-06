package app.u_business.data.repo

import app.u_business.domain.repo.EventsRepository
import app.u_business.presentation.ui.eventlist.response.EventItem

//TODO(Изменить на получение данных с сервера)

class EventsRepoImpl : EventsRepository {

    override suspend fun getEventList(): List<EventItem> = MockMainData.createEvents()
    override suspend fun searchEvents(word: String): List<EventItem> = MockMainData.createEvents()

}