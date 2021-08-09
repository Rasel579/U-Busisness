package app.u_business.data.repo

import app.u_business.data.network.api.BackEndRepo
import app.u_business.domain.repo.EventsRepository
import app.u_business.presentation.ui.eventlist.response.EventItem

class EventsRepoImpl : EventsRepository {

    override suspend fun getEventList(): List<EventItem> = BackEndRepo.api.getEvents()
}