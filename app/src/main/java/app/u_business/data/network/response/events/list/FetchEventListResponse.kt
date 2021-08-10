package app.u_business.presentation.ui.eventlist.response

import android.annotation.SuppressLint
import app.u_business.data.network.response.news.fetch_news.dateFormat
import app.u_business.data.network.response.news.fetch_news.serverDateFormat
import app.u_business.domain.model.Event
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class FetchEventListResponse(

    @field:SerializedName("Event")
    val event: List<EventItem?>? = null
)

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
) {

    @SuppressLint("SimpleDateFormat")
    fun getDateFormat(): String {
        val date = SimpleDateFormat("dd MMMM HH:mm").format(Date())
        return date
    }

    fun toEven() = Event(
        banner ?: "", title ?: "",
        try {
            dateFormat.format(serverDateFormat.parse(date))
        } catch (e: Exception) {
            ""
        }
    )
}
