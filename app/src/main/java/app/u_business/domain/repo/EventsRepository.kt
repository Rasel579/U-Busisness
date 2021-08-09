package app.u_business.presentation.ui.eventlist.repo.eventlist

import app.u_business.data.network.api.BackEndRepo
import app.u_business.data.network.query.cards.SearchBody
import app.u_business.presentation.ui.eventlist.response.EventItem

interface EventListRepository {

    suspend fun getEventList(): List<EventItem>
    suspend fun searchEvents(word: String) = BackEndRepo.api.searchEventByWord(SearchBody(word))
}