package app.u_business.presentation.ui.offers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.u_business.R
import app.u_business.databinding.ItemSmallOfferBinding
import app.u_business.domain.model.Offer
import app.u_business.presentation.ui.news.FullscreenCardViewHolder
import com.bumptech.glide.Glide

class OffersAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val DEFAULT_TYPE = 0
        private const val FULLSCREEN_TYPE = 1
    }

    var data: List<Offer> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return if (position % 7 == 0 && position != 0)
            FULLSCREEN_TYPE
        else
            DEFAULT_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType) {
            DEFAULT_TYPE -> SmallOfferViewHolder.create(parent)
            else -> FullscreenCardViewHolder.create(parent)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            DEFAULT_TYPE -> { (holder as SmallOfferViewHolder).bind(data[position]) }
            FULLSCREEN_TYPE -> {
                val item = data[position]
                val title = "Скидка ${item.discount}% на ${item.goods}"
                val date = "до ${item.date}"
                (holder as FullscreenCardViewHolder).bind(item.image, title, date)
            }
        }
    }

    override fun getItemCount(): Int = data.size
}

class SmallOfferViewHolder(
    private val binding: ItemSmallOfferBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): SmallOfferViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSmallOfferBinding.inflate(inflater, parent, false)
            return SmallOfferViewHolder(binding)
        }
    }

    fun bind(offer: Offer) {
        with(binding) {
            Glide.with(root)
//                .load(news.image)
                .load(R.drawable.test_image)
                .circleCrop()
                .into(itemSmallOfferImage)

            discount = offer.discount
            goods = offer.goods
            date = offer.date
            itemSmallOfferDateText.text = offer.date
        }
    }
}

