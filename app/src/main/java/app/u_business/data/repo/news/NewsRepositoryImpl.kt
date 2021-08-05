package app.u_business.data.repo.news

import app.u_business.data.network.api.BackEndRepo
import app.u_business.data.network.query.news.NewBody
import app.u_business.data.network.response.message_response.MessageResponse
import app.u_business.domain.repo.news.NewsRepository
import okhttp3.RequestBody

class NewsRepositoryImpl : NewsRepository {
    override suspend fun createNews(news: NewBody, file: RequestBody): MessageResponse {
        TODO("Not yet implemented")
    }

    override suspend fun editNews(news: NewBody, file: RequestBody): MessageResponse {
        TODO("Not yet implemented")
    }

    override suspend fun openNewsItem(id: String) = BackEndRepo.api.openNews(id)
    override suspend fun getNewsList() = BackEndRepo.api.getNewsList()
    override suspend fun searchNews(word: String) = BackEndRepo.api.searchNews(word)
}