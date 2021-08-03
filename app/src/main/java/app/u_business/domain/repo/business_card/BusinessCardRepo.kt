package app.u_business.domain.repo.business_card

import app.u_business.data.network.query.cards.BusinessCardBody
import app.u_business.data.network.response.business_card.card_response.BusinessCardResponse
import app.u_business.data.network.response.message_response.MessageResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface BusinessCardRepo {
    suspend fun getBusinessCard(accessToken : String) : BusinessCardResponse
    suspend fun postEditBusinessCard(authToken: String?, businessCardBody: MutableMap<String, RequestBody>, file: MultipartBody.Part) : MessageResponse
}