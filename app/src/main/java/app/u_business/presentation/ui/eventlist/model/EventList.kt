package app.u_business.presentation.ui.eventlist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventList(
    val image: String,
    val title: String,
    val date: String,
    val category: String,
    val usersId: List<Int>
): Parcelable