package app.u_business.data.network.response.message_response

import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("ru")
    val messageRu: String?,
    @SerializedName("en")
    val messageEn: String?,
    @SerializedName("de")
    val messageDe: String?
)