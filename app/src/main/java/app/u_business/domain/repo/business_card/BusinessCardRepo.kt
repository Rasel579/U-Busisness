package app.u_business.domain.repo.business_card

import app.u_business.data.network.query.cards.BusinessCardBody
import app.u_business.data.network.query.cards.FavoriteCardBody
import app.u_business.data.network.query.cards.SearchBody
import app.u_business.data.network.response.business_card.card_response.BusinessCardResponse
import app.u_business.data.network.response.business_card.card_response.BusinessCardResponseItem
import app.u_business.data.network.response.business_card.verified_card_response.FetchActivatedBusinessCards
import app.u_business.data.network.response.message_response.MessageResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface BusinessCardRepo {
    suspend fun getBusinessCard() : BusinessCardResponse
    suspend fun postEditBusinessCard( businessCardBody: MutableMap<String, RequestBody>, file: MultipartBody.Part) : MessageResponse
    suspend fun getAllBSCards() : FetchActivatedBusinessCards
    suspend fun  searchCards(search: SearchBody) : FetchActivatedBusinessCards
    suspend fun addFavoriteCard(card : FavoriteCardBody) : MessageResponse
    suspend fun getFavoritesCards() : FetchActivatedBusinessCards
}