package app.u_business.data.repo.business_card

import app.u_business.data.network.api.BackEndRepo
import app.u_business.data.network.query.cards.FavoriteCardBody
import app.u_business.data.network.query.cards.SearchBody
import app.u_business.data.network.response.business_card.card_response.BusinessCardResponse
import app.u_business.data.network.response.business_card.verified_card_response.FetchActivatedBusinessCards
import app.u_business.data.network.response.message_response.MessageResponse
import app.u_business.domain.repo.business_card.BusinessCardRepo
import okhttp3.MultipartBody
import okhttp3.RequestBody

class BusinessCardRepoImpl: BusinessCardRepo {
    override suspend fun getBusinessCard() = BackEndRepo.api.getBusinessCard()
    override suspend fun postEditBusinessCard( businessCardBody: MutableMap<String, RequestBody>, file: MultipartBody.Part) = BackEndRepo.api.editBusinessCard(businessCardBody, file)
    override suspend fun getAllBSCards(): FetchActivatedBusinessCards = BackEndRepo.api.getActiveBusinessCards()
    override suspend fun searchCards(search: SearchBody) = BackEndRepo.api.searchBusinessCards(search)
    override suspend fun addFavoriteCard(card: FavoriteCardBody): MessageResponse = BackEndRepo.api.addCardToFavorite(card)
    override suspend fun getFavoritesCards(): FetchActivatedBusinessCards = BackEndRepo.api.getFavoritesCards()
}