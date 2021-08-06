package app.u_business.data.repo

import app.u_business.data.network.api.BackEndRepo
import app.u_business.data.network.api.ServiceApi
import app.u_business.data.network.response.events.fetch_event.FetchEventResponseItem
import app.u_business.data.network.response.news.fetch_news.FetchNewsResponseItem
import app.u_business.data.network.response.offers.fetch_user_offers.FetchUserOffersResponseItem
import app.u_business.domain.repo.main.MainRepo

class MainRepoImpl(): MainRepo {
    override suspend fun getNews(): List<Any> = BackEndRepo.api.getNewsList()

    override suspend fun getEvents(): List<Any> = BackEndRepo.api.getEvents()

    override suspend fun getSpecialOffers(): List<Any> = BackEndRepo.api.getOffers()
}