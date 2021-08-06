package app.u_business.presentation.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.u_business.data.network.response.events.fetch_event.FetchEventResponseItem
import app.u_business.data.network.response.news.fetch_news.FetchNewsResponseItem
import app.u_business.data.network.response.offers.fetch_user_offers.FetchUserOffersResponseItem
import app.u_business.domain.repo.main.MainRepo
import app.u_business.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

data class HomeData(
    val news: List<FetchNewsResponseItem> = listOf(),
    val events: List<FetchEventResponseItem> = listOf(),
    val offers: List<FetchUserOffersResponseItem> = listOf()
)

sealed class HomeState {
    data class Success(val data: HomeData) : HomeState()
    data class Error(val error: Throwable) : HomeState()
    object Loading : HomeState()
}

class HomeVM(app: Application, private val repo: MainRepo) : BaseViewModel(app) {
    init {
        viewModelScope.launch {
            with(repo) {
                val events = async { getEvents() }
                val news = async { getNews() }
                val offers = async { getSpecialOffers() }


                awaitAll(events, news, offers)

                Log.d(TAG, "$events: $news $offers ")
//                state.postValue(HomeState.Success(HomeData(news.await(), events.await(), offers.await())))
            }
        }
    }

    val state = MutableLiveData<HomeState>(HomeState.Loading)

    companion object {
        private const val TAG = "HomeVM"
    }

}