package app.u_business.domain.repo.main

import app.u_business.data.network.response.events.fetch_event.FetchEventResponseItem
import app.u_business.data.network.response.news.fetch_news.FetchNewsResponseItem
import app.u_business.data.network.response.offers.fetch_user_offers.FetchUserOffersResponseItem

interface MainRepo {

    suspend fun getNews(): List<Any>

    suspend fun getEvents(): List<Any>

    suspend fun getSpecialOffers(): List<Any>
}