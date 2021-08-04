package app.u_business.presentation.ui.eventlist.`interface`

import app.u_business.domain.model.EventList

interface OnItemEventClickListener {

    fun onItemViewClick(eventList: EventList)

}