package app.u_business.presentation.ui.eventlist.repo.eventlist

import app.u_business.data.network.api.ServiceApi
import app.u_business.presentation.ui.eventlist.converter.convertEventDtoToDomain
import app.u_business.presentation.ui.eventlist.model.EventList
import app.u_business.presentation.ui.eventlist.response.EventItem
import app.u_business.presentation.ui.eventlist.response.FetchUserProfileDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventListRepositoryImpl(private val apiServiceEvent: ServiceApi):  EventListRepository {

    override suspend fun fetcheventList(idUsers: String): List<EventList> = withContext(Dispatchers.IO)   {

        // get users
        val users = apiServiceEvent.getProfileUser(idUsers)
        // get eventsList
        val eventListDto = apiServiceEvent.getEvents()
        // get eventsList model
        val eventsListModel = convertEventDtoToDomain(eventListDto as List<EventItem>,
            users.userList as List<FetchUserProfileDto>
        )

        return@withContext eventsListModel
    }
}