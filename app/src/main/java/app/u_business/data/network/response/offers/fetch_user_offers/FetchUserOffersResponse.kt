package app.u_business.data.network.response.offers.fetch_user_offers

import app.u_business.domain.model.Offer

class FetchUserOffersResponse : ArrayList<FetchUserOffersResponseItem>() {

    fun toOffers(count: Int = Int.MAX_VALUE): List<Offer> {
        return java.util.ArrayList(subList(0, if (size > count) count else size - 1).map {
            Offer(
                /*FIXME модели не совпадают*/
                0, "goods",
                "12.12.2002 11:23",
                ""
            )
        })
    }
}