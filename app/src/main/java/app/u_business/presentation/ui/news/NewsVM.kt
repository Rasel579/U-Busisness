package app.u_business.presentation.ui.news

import android.app.Application
import androidx.lifecycle.MutableLiveData
import app.u_business.data.network.response.news.fetch_news.FetchNewsResponse
import app.u_business.data.network.response.news.open_news.OpenNewsResponse
import app.u_business.domain.repo.news.NewsRepository
import app.u_business.presentation.ui.base.BaseViewModel

class NewsVM(app: Application, private val repo: NewsRepository) : BaseViewModel(app) {
    val ldFetchNews: MutableLiveData<ResponseState<FetchNewsResponse>> = MutableLiveData()
    val ldNewsItem: MutableLiveData<ResponseState<OpenNewsResponse>> = MutableLiveData()

    fun requestNewsList() = getData(ldFetchNews) { repo.getNewsList() }
    fun requestNewsItem(id: String) = getData(ldNewsItem) { repo.openNewsItem(id) }
    fun requestSearchNews(word: String) = getData(ldFetchNews) { repo.searchNews(word) }
}