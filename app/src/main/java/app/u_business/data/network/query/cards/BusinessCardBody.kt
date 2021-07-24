package app.u_business.data.network.query.cards

data class BusinessCardBody(
    val name: String?,
    val country: String?,
    val address: String?,
    val industry: String?,
    val tags: String?,
    val idUser: Int?
)