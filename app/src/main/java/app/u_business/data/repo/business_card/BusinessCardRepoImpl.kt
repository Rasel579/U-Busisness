package app.u_business.data.repo.business_card

import app.u_business.data.network.api.BackEndRepo
import app.u_business.domain.repo.business_card.BusinessCardRepo
import okhttp3.MultipartBody
import okhttp3.RequestBody

class BusinessCardRepoImpl: BusinessCardRepo {
    override suspend fun getBusinessCard(accessToken: String) = BackEndRepo.api.getBusinessCard("Bearer $accessToken")
    override suspend fun postEditBusinessCard(authToken: String?, businessCardBody: MutableMap<String, RequestBody>, file: MultipartBody.Part) = BackEndRepo.api.editBusinessCard("Bearer $authToken", businessCardBody, file)
}