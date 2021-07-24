package app.u_business.data.network.query.offers

data class EditableOfferBody(
    val idOffer: Int?,
    val protocol: String?,
    val term: String?,
    val text: String?,
    val title: String?
)