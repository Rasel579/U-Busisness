package app.u_business.data.repo

import app.u_business.data.network.api.BackEndRepo
import app.u_business.data.network.response.news.fetch_news.FetchNewsResponse
import app.u_business.domain.repo.main.MainRepo
import app.u_business.presentation.ui.eventlist.response.EventItem

class MainRepoImpl() : MainRepo {
    override suspend fun getNews(): FetchNewsResponse = BackEndRepo.api.getNewsList()

    override suspend fun getEvents(): List<EventItem> = BackEndRepo.api.getEvents()

    override suspend fun getSpecialOffers(): List<Any> = BackEndRepo.api.getOffers()
}