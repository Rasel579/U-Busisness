package app.u_business.presentation.ui.profile.special_offers

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.u_business.R
import app.u_business.domain.model.Offer

class SpecialOffersAdapter : RecyclerView.Adapter<SpecialOfferViewHolder>() {
    var data: List<Offer> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialOfferViewHolder =
        SpecialOfferViewHolder.create(parent)

    override fun onBindViewHolder(holder: SpecialOfferViewHolder, position: Int) {
        holder.bind(data[position], when(position % 4) {
            0 -> { R.color.mona_lisa }
            1 -> { R.color.violet }
            2 -> { R.color.light_blue }
            3 -> { R.color.orange }
            else -> { R.color.mona_lisa }
        })
    }

    override fun getItemCount(): Int = data.size
}