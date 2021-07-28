package app.u_business.data.network.response.user.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("userId")
    val userId: Int?,
    @SerializedName("role")
    val role : String?,
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("refreshToken")
    val refreshToken: String?
)