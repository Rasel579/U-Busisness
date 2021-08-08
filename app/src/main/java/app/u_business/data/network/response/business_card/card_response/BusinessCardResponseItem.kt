package app.u_business.data.network.response.business_card.card_response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BusinessCardResponseItem(
    @SerializedName("user")
    val user: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("avatarurl")
    val avatarUrl: String?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("urllogo")
    val urlLogo: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("adress")
    val address: String?,
    @SerializedName("tags")
    val tags: String?
): Parcelable