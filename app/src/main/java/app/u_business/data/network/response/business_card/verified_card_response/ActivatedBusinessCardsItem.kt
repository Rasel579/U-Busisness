package app.u_business.data.network.response.business_card.verified_card_response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ActivatedBusinessCardsItem(
    val userId: Int?,
    val user: String?,
    val avatarurl: String?,
    val status: String?,
    val company: String?,
    val industry: String?
): Parcelable