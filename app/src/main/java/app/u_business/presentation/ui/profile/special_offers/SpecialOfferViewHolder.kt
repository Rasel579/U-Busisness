package app.u_business.presentation.ui.profile.special_offers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.u_business.databinding.ItemOfferBinding
import app.u_business.domain.model.Offer

class SpecialOfferViewHolder(
    private val binding: ItemOfferBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): SpecialOfferViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemOfferBinding.inflate(inflater, parent, false)
            return SpecialOfferViewHolder(binding)
        }
    }

    fun bind(offer: Offer, backgroundTint: Int, onClick: ((Offer) -> Unit)?) {
        with(binding) {
            root.apply {
                backgroundTintList = ContextCompat.getColorStateList(root.context, backgroundTint)
                setOnClickListener { onClick?.let { click -> click(offer) } }
            }
            discount = offer.discount
            goods = offer.goods
            executePendingBindings()
        }
    }
}