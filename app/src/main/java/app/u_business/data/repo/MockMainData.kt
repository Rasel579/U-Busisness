package app.u_business.data.repo

import app.u_business.data.network.response.message_response.MessageResponse
import app.u_business.data.network.response.news.fetch_news.FetchNewsResponse
import app.u_business.data.network.response.news.fetch_news.FetchNewsResponseItem
import app.u_business.data.network.response.news.open_news.OpenNewsResponse
import app.u_business.data.network.response.offers.fetch_user_offers.FetchUserOffersResponse
import app.u_business.data.network.response.offers.fetch_user_offers.FetchUserOffersResponseItem
import app.u_business.presentation.ui.eventlist.response.EventItem

object MockMainData {
    fun createNewsResponse() : FetchNewsResponse = FetchNewsResponse().apply {
        add(FetchNewsResponseItem(
            id = 1,
            title = "News",
            date = "12/01/2021",
            banner = "Banner"
        ))
    }

    fun createEvents(): List<EventItem> = mutableListOf<EventItem>().apply {
        add(
            EventItem(
                date = "01/12/2021",
                banner = "Banner",
                id = 1,
                text = "text messages",
                title = "Title",
                category = "category"
            )
        )
    }

    fun createSpecialOffers(): FetchUserOffersResponse = FetchUserOffersResponse().apply {
        add(FetchUserOffersResponseItem(
            id = 1,
            img = null,
            title = "offer",
            protocol = "string",
            timecreation = "01/10/2021",
            term = "@term",
            text = "text text",
            idcreator = 1
        ))
    }

    fun createMessageResponse() = MessageResponse(
        messageRu = "Good",
        messageEn = "GoodEn",
        messageDe = "GoodDE"
    )

    fun createOpenNewsResponse() = OpenNewsResponse(
        banner = "banner",
        date = "12/12/2021",
        id = 1,
        text = "messages",
        title = "Title"
    )
}