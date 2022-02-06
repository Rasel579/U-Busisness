package app.u_business.data.repo

import app.u_business.data.network.response.news.fetch_news.FetchNewsResponse
import app.u_business.data.network.response.offers.fetch_user_offers.FetchUserOffersResponse
import app.u_business.domain.repo.main.MainRepo
import app.u_business.presentation.ui.eventlist.response.EventItem
//TODO(Изменить на получение данных с сервера)
class MainRepoImpl() : MainRepo {
    override suspend fun getNews(): FetchNewsResponse = MockMainData.createNewsResponse()

    override suspend fun getEvents(): List<EventItem> = MockMainData.createEvents()

    override suspend fun getSpecialOffers(): FetchUserOffersResponse = MockMainData.createSpecialOffers()
}