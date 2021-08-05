package app.u_business.data.network.response.news.fetch_news

import app.u_business.domain.model.News
import java.text.SimpleDateFormat
import java.util.*

val serverDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())

class FetchNewsResponse : ArrayList<FetchNewsResponseItem>() {
    fun toNewsWithEmpty(): List<News?> {
        return toNews().toMutableList<News?>().apply { add(null) }
    }

    fun toNews(): List<News> {
        return ArrayList(map {
            News(
                it.banner ?: "", it.title ?: "",
                try {
                    dateFormat.format(serverDateFormat.parse(it.date))
                } catch (e: Exception) {
                    ""
                },
                ""
            )
        })
    }
}