package app.u_business.data.network.response.eventlist

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class FetchEventListResponse(

	@field:SerializedName("Event")
	val event: List<EventItem?>? = null
) : Parcelable

@Parcelize
data class EventItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("banner")
	val banner: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("users")
	val users: List<Int?>? = null
) : Parcelable {

	@SuppressLint("SimpleDateFormat")
	fun getDateFormat(): String {
		val date = SimpleDateFormat("dd MMMM HH:mm").format(Date())
		return date
	}
}
