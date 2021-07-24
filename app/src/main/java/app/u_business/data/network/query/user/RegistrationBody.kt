package app.u_business.data.network.query.user

data class RegistrationBody(
    val email: String?,
    val name: String?,
    val password: String?
)