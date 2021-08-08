package app.u_business.presentation.ui.business_cards

import app.u_business.data.network.response.business_card.verified_card_response.ActivatedBusinessCardsItem

interface OnLongClickListener {
    fun onItemLongClickListener(businessCardResponseItem: ActivatedBusinessCardsItem)
}