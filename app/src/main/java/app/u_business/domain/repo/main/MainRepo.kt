package app.u_business.domain.repo.main

import app.u_business.data.network.response.news.fetch_news.FetchNewsResponse
import app.u_business.presentation.ui.eventlist.response.EventItem

//import app.u_business.data.network.response.events.fetch_event.FetchEventResponseItem
//import app.u_business.data.network.response.news.fetch_news.FetchNewsResponseItem

//import app.u_business.data.network.response.offers.fetch_user_offers.FetchUserOffersResponseItem

interface MainRepo {

    suspend fun getNews(): FetchNewsResponse

    suspend fun getEvents(): List<EventItem>

    suspend fun getSpecialOffers(): List<Any>
}