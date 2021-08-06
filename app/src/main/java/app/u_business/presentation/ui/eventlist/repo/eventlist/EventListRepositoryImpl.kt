package app.u_business.presentation.ui.eventlist.repo.eventlist

import app.u_business.data.network.api.BackEndRepo
import app.u_business.presentation.ui.eventlist.response.EventItem

class EventListRepositoryImpl : EventListRepository {

    override suspend fun getEventList(): List<EventItem> = BackEndRepo.api.getEvents()
}