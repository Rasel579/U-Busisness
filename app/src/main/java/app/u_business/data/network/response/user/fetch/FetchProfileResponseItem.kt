package app.u_business.data.network.response.user.fetch

data class FetchProfileResponseItem(
    val email: String?,
    val idUser: Int?,
    val lang: String?,
    val name: String?,
    val tel: String?,
    val message: String?
)