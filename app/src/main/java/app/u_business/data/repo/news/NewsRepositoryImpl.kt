package app.u_business.data.repo.news

import app.u_business.data.network.query.news.NewBody
import app.u_business.data.network.response.message_response.MessageResponse
import app.u_business.data.repo.MockMainData
import app.u_business.domain.repo.news.NewsRepository
import okhttp3.RequestBody
//TODO(Изменить на получение данных с сервера)
class NewsRepositoryImpl : NewsRepository {
    override suspend fun createNews(news: NewBody, file: RequestBody): MessageResponse {
        TODO("Not yet implemented")
    }

    override suspend fun editNews(news: NewBody, file: RequestBody): MessageResponse {
        TODO("Not yet implemented")
    }

    override suspend fun openNewsItem(id: String) = MockMainData.createOpenNewsResponse()
    override suspend fun getNewsList() = MockMainData.createNewsResponse()
    override suspend fun searchNews(word: String) = MockMainData.createNewsResponse()
}