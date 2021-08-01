package app.u_business.presentation.ui.profile.business_card

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.u_business.data.network.query.cards.BusinessCardBody
import app.u_business.data.network.response.business_card.card_response.BusinessCardResponse
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.domain.repo.business_card.BusinessCardRepo
import app.u_business.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.koin.ext.getOrCreateScope
import kotlin.Exception

sealed class CardFetchAction{
    data class Success(val businessCardResponseItem: BusinessCardResponse?) : CardFetchAction()
    data class SuccessEdit(val message: String?): CardFetchAction()
    data class Error(val message: String?): CardFetchAction()
}
data class FetchCardsEvent(val action: CardFetchAction)
class BusinessCardVM
    (app: Application,
     private val repo : BusinessCardRepo,
     private val authRepo: AuthRepository
) : BaseViewModel(app){
   val fetchCardsEvent = MutableLiveData<FetchCardsEvent>()
   fun fetchBusinessCard(){
       viewModelScope.launch(Dispatchers.IO) {
           try {
               authRepo.getAccessToken()?.let { Log.e("my access token is", it) }
               val res = authRepo.getAccessToken()?.let { repo.getBusinessCard(it) }
               fetchCardsEvent.postValue(FetchCardsEvent(CardFetchAction.Success(res)))

           }catch (exception: Exception){
               fetchCardsEvent.postValue(FetchCardsEvent(CardFetchAction.Error(exception.message)))
           }
       }
   }

    fun postEditCard(businessCardBody: BusinessCardBody){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                 val user = authRepo.getUser()
                businessCardBody.idUser = user.userId?.toInt()
                Log.e("login shares", authRepo.getUser().toString())
                val res = repo.postEditBusinessCard(businessCardBody, RequestBody.create("jpg".toMediaTypeOrNull(), "files.jpg"))
                 fetchCardsEvent.postValue(FetchCardsEvent(CardFetchAction.SuccessEdit(res.messageRu)))
            }catch (exception: Exception){
                fetchCardsEvent.postValue(FetchCardsEvent(CardFetchAction.Error(exception.message)))
            }
        }
    }
}