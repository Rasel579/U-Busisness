package app.u_business.presentation.ui.profile.special_offers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.u_business.R
import app.u_business.domain.model.Offer

class SpecialOffersAdapter(
    private val onClick: ((Offer) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val DEFAULT_TYPE = 0
        private const val PLACEHOLDER_TYPE = 1
    }

    var data: List<Offer?> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int =
        if (data[position] == null) PLACEHOLDER_TYPE else DEFAULT_TYPE


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            DEFAULT_TYPE -> {
                SpecialOfferViewHolder.create(parent)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_offer_more, parent, false)
                SpecialOfferMoreViewHolder(view)
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == DEFAULT_TYPE)
            (holder as SpecialOfferViewHolder).bind(
                data[position]!!,
                when (position % 4) {
                    0 -> {
                        R.color.mona_lisa
                    }
                    1 -> {
                        R.color.violet
                    }
                    2 -> {
                        R.color.light_blue
                    }
                    3 -> {
                        R.color.orange
                    }
                    else -> {
                        R.color.mona_lisa
                    }
                },
                onClick
            )
    }

    override fun getItemCount(): Int = data.size
}

class SpecialOfferMoreViewHolder(v: View) : RecyclerView.ViewHolder(v)