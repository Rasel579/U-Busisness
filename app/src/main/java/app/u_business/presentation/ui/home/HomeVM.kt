package app.u_business.presentation.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.u_business.data.network.response.news.fetch_news.FetchNewsResponse
import app.u_business.data.network.response.offers.fetch_user_offers.FetchUserOffersResponseItem
import app.u_business.domain.repo.main.MainRepo
import app.u_business.presentation.ui.base.BaseViewModel
import app.u_business.presentation.ui.eventlist.response.EventItem
import app.u_business.presentation.ui.news.ResponseState
import app.u_business.presentation.ui.news.getData
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

data class HomeData(
    var news: FetchNewsResponse = FetchNewsResponse(),
    var events: List<EventItem> = listOf(),
    val offers: List<FetchUserOffersResponseItem> = listOf()
)

class HomeVM(app: Application, private val repo: MainRepo) : BaseViewModel(app) {


    val state: MutableLiveData<ResponseState<HomeData>> = MutableLiveData()

    fun requestNewsList() = getData(state) { getHomeData() }

    private suspend fun getHomeData(): HomeData {
        return suspendCoroutine { continuation ->
            viewModelScope.launch {
                with(repo) {
                    val hd = HomeData()
                    val eventsCor = async { getEvents() }
                    val newsCor = async { getNews() }
                    val offersCor = async { getSpecialOffers() }

                    hd.news = try {
                        newsCor.await()
                    } catch (ex: Exception) {
                        FetchNewsResponse()
                    }

                    hd.events = try {
                        eventsCor.await()
                    } catch (ex: Exception) {
                        listOf<EventItem>()
                    }
                    continuation.resume(hd)
                }
            }
        }
    }

    companion object {
        private const val TAG = "HomeVM"
    }

}