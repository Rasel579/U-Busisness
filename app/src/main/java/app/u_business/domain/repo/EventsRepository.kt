package app.u_business.domain.repo

import app.u_business.presentation.ui.eventlist.response.EventItem

interface EventsRepository {

    suspend fun getEventList(): List<EventItem>
    suspend fun searchEvents(word: String): List<EventItem>
}