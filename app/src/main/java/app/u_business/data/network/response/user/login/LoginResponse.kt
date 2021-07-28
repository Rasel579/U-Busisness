package app.u_business.data.network.response.user.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val message: String?,
    val userId: Int?,
    val role : String?,
    @SerializedName("AccessToken")
    val accessToken: String?,
    @SerializedName("RefreshToken")
    val refreshToken: String?
)