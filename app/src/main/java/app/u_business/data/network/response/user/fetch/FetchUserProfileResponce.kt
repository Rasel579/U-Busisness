package app.u_business.data.network.response.user.fetch

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserList(

	@field:SerializedName("UserPojo")
	val userList: List<UserItem?>? = null
) : Parcelable

@Parcelize
data class UserItem(

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
