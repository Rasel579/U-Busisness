package app.u_business.data.network.response.news.fetch_news

import app.u_business.domain.model.News
import java.text.SimpleDateFormat
import java.util.*

val serverDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())

class FetchNewsResponse : ArrayList<FetchNewsResponseItem>() {
    fun toNewsWithEmpty(count: Int): List<News?> {
        return toNews(count).toMutableList<News?>().apply { add(null) }
    }

    fun toNews(count: Int = Int.MAX_VALUE): List<News> {
        return ArrayList(subList(0, if (size > count) count else size - 1).map {
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