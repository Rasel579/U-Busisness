package app.u_business.data.network.response.reg
import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("refreshToken")
    val refreshToken: String?,
    @SerializedName("role")
    val role: String?,
    @SerializedName("userId")
    val userId: Int?
)