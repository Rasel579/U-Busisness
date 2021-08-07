package app.u_business.data.repo.business_card

import app.u_business.data.network.api.BackEndRepo
import app.u_business.domain.repo.business_card.BusinessCardRepo
import okhttp3.MultipartBody
import okhttp3.RequestBody

class BusinessCardRepoImpl: BusinessCardRepo {
    override suspend fun getBusinessCard() = BackEndRepo.api.getBusinessCard()
    override suspend fun postEditBusinessCard( businessCardBody: MutableMap<String, RequestBody>, file: MultipartBody.Part) = BackEndRepo.api.editBusinessCard(businessCardBody, file)
}