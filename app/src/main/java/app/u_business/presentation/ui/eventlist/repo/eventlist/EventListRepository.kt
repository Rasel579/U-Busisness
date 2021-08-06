package app.u_business.presentation.ui.eventlist.repo.eventlist

import app.u_business.presentation.ui.eventlist.response.EventItem

interface EventListRepository {

    suspend fun getEventList(): List<EventItem>
}