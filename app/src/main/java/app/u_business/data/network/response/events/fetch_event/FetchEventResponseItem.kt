package app.u_business.data.network.response.events.fetch_event

data class FetchEventResponseItem(
    val id: Int?,
    val title: String?,
    val date: String?,
    val text: String?,
    val users: List<Int>?,
    val banner: String?,
    val category: String?
)