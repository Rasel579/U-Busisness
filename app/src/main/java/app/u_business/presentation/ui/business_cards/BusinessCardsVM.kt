package app.u_business.presentation.ui.business_cards

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.u_business.data.network.query.cards.FavoriteCardBody
import app.u_business.data.network.query.cards.SearchBody
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.domain.repo.business_card.BusinessCardRepo
import app.u_business.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BusinessCardsVM(
    app: Application,
    private val repo: BusinessCardRepo,
    private val authRepo: AuthRepository
) : BaseViewModel(app) {
    val stateLiveData = MutableLiveData<Any>()
    fun getBusinessCards() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = repo.getAllBSCards()
                Log.e("bs_cards", res.toString())
                stateLiveData.postValue(res)
            } catch (e: Throwable) {
                stateLiveData.postValue(e.message)
            }

        }
    }

    fun searchBusinessCards(searchWord: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = repo.searchCards(SearchBody(searchWord))
                Log.e("search_bs_cards", res.toString())
                stateLiveData.postValue(res)
            } catch (e: Throwable) {
                stateLiveData.postValue(e.message)
            }

        }
    }


    fun addFavoriteCard(idCard: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userId = authRepo.getUser().userId
                val res = repo.addFavoriteCard(FavoriteCardBody(idCard, userId))
                Log.e("favorite", res.toString())
                stateLiveData.postValue(res.messageRu!!)
            } catch (e: Throwable) {
                stateLiveData.postValue(e.message)
            }

        }
    }

    fun getFavoritesCards() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = repo.getFavoritesCards()
                Log.e("get favorite", res.toString())
                stateLiveData.postValue(res)
            } catch (e: Throwable) {
                stateLiveData.postValue(e.message)
            }

        }
    }

}