package app.u_business.presentation.ui.profile.business_card

import android.app.Application
import androidx.core.net.toUri
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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File

sealed class CardFetchAction {
    data class Success(val businessCardResponseItem: BusinessCardResponse?) : CardFetchAction()
    data class SuccessEdit(val message: String?) : CardFetchAction()
    data class Error(val message: String?) : CardFetchAction()
}

data class FetchCardsEvent(val action: CardFetchAction)
class BusinessCardVM
    (
    val app: Application,
    private val repo: BusinessCardRepo,
    private val authRepo: AuthRepository
) : BaseViewModel(app) {
    companion object {
        private const val MULTIPART_DATA = "multipart/form-data"
    }

    val fetchCardsEvent = MutableLiveData<FetchCardsEvent>()
    fun fetchBusinessCard() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = repo.getBusinessCard()
                fetchCardsEvent.postValue(FetchCardsEvent(CardFetchAction.Success(res)))
            } catch (exception: HttpException) {
                fetchCardsEvent.postValue(FetchCardsEvent(CardFetchAction.Error(exception.message)))
            }
        }
    }

    fun postEditCard(businessCardBody: BusinessCardBody, uriImage: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val authToken = authRepo.getAccessToken()
                businessCardBody.idUser = authRepo.getUser().userId
                val file = File(uriImage)
                val filePart = file.asRequestBody(uriImage?.let {
                    app.contentResolver.getType(it.toUri())?.toMediaTypeOrNull()
                })
                val multipartBody = MultipartBody.Part.createFormData("image", file.name, filePart)
                val partMapBody = mutableMapOf<String, RequestBody>()
                partMapBody["name"] = businessCardBody.name.toString()
                    .toRequestBody(MULTIPART_DATA.toMediaTypeOrNull())
                partMapBody["country"] = businessCardBody.country.toString()
                    .toRequestBody(MULTIPART_DATA.toMediaTypeOrNull())
                partMapBody["adress"] = businessCardBody.address.toString()
                    .toRequestBody(MULTIPART_DATA.toMediaTypeOrNull())
                partMapBody["industry"] = businessCardBody.industry.toString()
                    .toRequestBody(MULTIPART_DATA.toMediaTypeOrNull())
                partMapBody["tags"] = businessCardBody.tags.toString()
                    .toRequestBody(MULTIPART_DATA.toMediaTypeOrNull())

                val res = repo.postEditBusinessCard( partMapBody, multipartBody)
                fetchCardsEvent.postValue(FetchCardsEvent(CardFetchAction.SuccessEdit(res.messageRu)))
            } catch (exception: Exception) {
                fetchCardsEvent.postValue(FetchCardsEvent(CardFetchAction.Error(exception.message)))
            }
        }
    }
}