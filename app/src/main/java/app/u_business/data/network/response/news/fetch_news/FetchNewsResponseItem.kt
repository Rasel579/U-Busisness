package app.u_business.data.network.response.news.fetch_news

import com.google.gson.annotations.SerializedName



data class FetchNewsResponseItem(
    @field:SerializedName("id")
    val id: Int?,
    @field:SerializedName("title")
    val title: String?,
    @field:SerializedName("date")
    val date: String?,
    @field:SerializedName("banner")
    val banner: String?
)