package app.u_business.presentation.ui.eventlist.interfaces

import app.u_business.data.network.response.events.fetch_event.FetchEventResponseItem

interface OnItemEventClickListener {

    fun onItemViewClick(eventList: List<FetchEventResponseItem>)

}