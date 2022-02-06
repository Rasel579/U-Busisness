package app.u_business.presentation.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.u_business.data.network.response.news.fetch_news.FetchNewsResponse
import app.u_business.data.network.response.offers.fetch_user_offers.FetchUserOffersResponse
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
    var events: List<EventItem?> = listOf(),
    var offers: FetchUserOffersResponse = FetchUserOffersResponse()
)

class HomeVM(app: Application, private val repo: MainRepo) : BaseViewModel(app) {
    val state: MutableLiveData<ResponseState<HomeData>> = MutableLiveData()

    fun requestHomeData() = getData(state) {
        Log.e("errorHome", "error home view model")
        getHomeData() }

    private suspend fun getHomeData(): HomeData {
        Log.e("errorHome", "error home view model")
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

                    hd.offers = try {
                        offersCor.await()
                    } catch (ex: Exception) {
                        FetchUserOffersResponse()
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