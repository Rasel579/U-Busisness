package app.u_business.presentation.ui.eventlist.converter

import app.u_business.presentation.ui.eventlist.model.EventList
import app.u_business.presentation.ui.eventlist.response.EventItem
import app.u_business.presentation.ui.eventlist.response.FetchUserProfileDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun convertEventDtoToDomain(fetchEventListResponse: List<EventItem>, userList: List<FetchUserProfileDto>
): List<EventList> =
    withContext(Dispatchers.Default) {
        val usersMap: Map<Int, FetchUserProfileDto> = userList.associateBy { it.idUser!!}
        fetchEventListResponse.map { eventItem: EventItem ->
            EventList(
            image=eventItem.banner.toString(),
            title=eventItem.title.toString(),
            date=eventItem.getDateFormat(),
            category=eventItem.category.toString(),
            usersId =eventItem.users!!.map {
                usersMap[it]!!.idUser!!.toInt()
            }
            )
        }
}