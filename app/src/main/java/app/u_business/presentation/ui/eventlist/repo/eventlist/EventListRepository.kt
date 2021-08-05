package app.u_business.presentation.ui.eventlist.repo.eventlist

import app.u_business.presentation.ui.eventlist.model.EventList

interface EventListRepository {

    suspend fun fetcheventList(idUsers: String): List<EventList>
}