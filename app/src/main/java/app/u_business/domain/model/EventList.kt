package app.u_business.domain.model

data class EventList(
    val image: String,
    val title: String,
    val date: String,
    val category: String,
    val usersId: List<Int>
)