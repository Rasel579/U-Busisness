package app.u_business.data.repo.business_card

import app.u_business.data.network.response.business_card.card_response.BusinessCardResponse
import app.u_business.data.network.response.business_card.card_response.BusinessCardResponseItem
import app.u_business.data.network.response.business_card.verified_card_response.ActivatedBusinessCardsItem
import app.u_business.data.network.response.business_card.verified_card_response.FetchActivatedBusinessCards

object MockBusinessCardResponse {
    fun createResponse(): BusinessCardResponse = BusinessCardResponse().apply {
        addAll(
            arrayListOf<BusinessCardResponseItem>(
                BusinessCardResponseItem(
                    user = "tap",
                    name = "string",
                    status = "string",
                    avatarUrl = null,
                    company = "company",
                    urlLogo = null,
                    country = "Russia",
                    address = "adress",
                    tags = "tag"
                )
            )
        )
    }

    fun createFetchActivatedBusinessCards(): FetchActivatedBusinessCards = FetchActivatedBusinessCards().apply {
        add(
            ActivatedBusinessCardsItem(
                userId = 1,
                user = "user",
                avatarurl = null,
                status = "payed",
                company = "company",
                industry = "industry"
            )
        )
    }
}

