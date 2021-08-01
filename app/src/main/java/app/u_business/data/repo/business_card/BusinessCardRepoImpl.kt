package app.u_business.data.repo.business_card

import app.u_business.data.network.api.BackEndRepo
import app.u_business.data.network.query.cards.BusinessCardBody
import app.u_business.domain.repo.business_card.BusinessCardRepo
import okhttp3.RequestBody

class BusinessCardRepoImpl: BusinessCardRepo {
    override suspend fun getBusinessCard(accessToken: String) = BackEndRepo.api.getBusinessCard("Bearer $accessToken")
    override suspend fun postEditBusinessCard(businessCardBody: BusinessCardBody, file: RequestBody) = BackEndRepo.api.editBusinessCard(businessCardBody, file)
}