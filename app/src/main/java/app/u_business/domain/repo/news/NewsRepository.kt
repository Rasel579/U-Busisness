package app.u_business.domain.repo.news

import app.u_business.data.network.query.news.NewBody
import app.u_business.data.network.response.message_response.MessageResponse
import app.u_business.data.network.response.news.fetch_news.FetchNewsResponse
import app.u_business.data.network.response.news.open_news.OpenNewsResponse
import okhttp3.RequestBody

interface NewsRepository {
    suspend fun createNews(news: NewBody, file: RequestBody): MessageResponse
    suspend fun editNews(news: NewBody, file: RequestBody): MessageResponse
    suspend fun openNewsItem(id: String): OpenNewsResponse
    suspend fun getNewsList(): FetchNewsResponse
    suspend fun searchNews(word: String): FetchNewsResponse
}