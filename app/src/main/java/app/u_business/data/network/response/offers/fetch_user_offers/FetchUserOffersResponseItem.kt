package app.u_business.data.network.response.offers.fetch_user_offers

data class FetchUserOffersResponseItem(
    val id: Int?,
    val img: String?,
    val title: String?,
    val protocol: String?,
    val timecreation: String?,
    val term: String?,
    val text: String?,
    val idcreator: Int?
)