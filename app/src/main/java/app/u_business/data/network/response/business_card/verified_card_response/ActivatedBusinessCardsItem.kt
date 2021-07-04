package app.u_business.data.network.response.business_card.verified_card_response

data class ActivatedBusinessCardsItem(
    val userId: Int?,
    val user: String?,
    val avatarurl: String?,
    val status: String?,
    val company: String?,
    val industry: String?
)