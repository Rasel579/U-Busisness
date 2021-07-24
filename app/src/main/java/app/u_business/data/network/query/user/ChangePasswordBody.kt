package app.u_business.data.network.query.user

data class ChangePasswordBody(
    val idUser: Int?,
    val newPassword: String?
)