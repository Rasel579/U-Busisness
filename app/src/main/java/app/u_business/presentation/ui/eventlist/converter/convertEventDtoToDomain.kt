package app.u_business.presentation.ui.eventlist.converter

import app.u_business.data.network.response.eventlist.EventItem
import app.u_business.data.network.response.user.fetch.UserItem
import app.u_business.domain.model.EventList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun convertEventDtoToDomain(fetchEventListResponse: List<EventItem>, userList: List<UserItem>
): List<EventList> =
    withContext(Dispatchers.Default) {
        val usersMap: Map<Int, UserItem> = userList.associateBy { it.idUser!!}
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