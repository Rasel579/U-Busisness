package app.u_business.presentation.ui.eventlist.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FetchUserProfileResponce(

	@field:SerializedName("UserPojo")
	val userList: List<FetchUserProfileDto?>? = null
) : Parcelable

@Parcelize
data class FetchUserProfileDto(

	@field:SerializedName("idUser")
	val idUser: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("tel")
	val tel: String? = null,

	@field:SerializedName("lang")
	val lang: String? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable
