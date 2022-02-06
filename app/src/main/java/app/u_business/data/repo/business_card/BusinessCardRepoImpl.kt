package app.u_business.data.repo.business_card

import app.u_business.data.network.query.cards.FavoriteCardBody
import app.u_business.data.network.query.cards.SearchBody
import app.u_business.data.network.response.business_card.card_response.BusinessCardResponse
import app.u_business.data.network.response.business_card.verified_card_response.FetchActivatedBusinessCards
import app.u_business.data.network.response.message_response.MessageResponse
import app.u_business.data.repo.MockMainData
import app.u_business.domain.repo.business_card.BusinessCardRepo
import okhttp3.MultipartBody
import okhttp3.RequestBody
//TODO(Изменить на получение данных с сервера)
class BusinessCardRepoImpl: BusinessCardRepo {
    override suspend fun getBusinessCard(): BusinessCardResponse = MockBusinessCardResponse.createResponse()
    override suspend fun postEditBusinessCard( businessCardBody: MutableMap<String, RequestBody>, file: MultipartBody.Part) = MockMainData.createMessageResponse()
    override suspend fun getAllBSCards(): FetchActivatedBusinessCards = MockBusinessCardResponse.createFetchActivatedBusinessCards()
    override suspend fun searchCards(search: SearchBody) = MockBusinessCardResponse.createFetchActivatedBusinessCards()
    override suspend fun addFavoriteCard(card: FavoriteCardBody): MessageResponse = MockMainData.createMessageResponse()
    override suspend fun getFavoritesCards(): FetchActivatedBusinessCards = MockBusinessCardResponse.createFetchActivatedBusinessCards()
}