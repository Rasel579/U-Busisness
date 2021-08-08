package app.u_business.presentation.ui.business_cards

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.u_business.R
import app.u_business.data.network.api.ApiUtils
import app.u_business.data.network.response.business_card.verified_card_response.ActivatedBusinessCardsItem
import app.u_business.data.network.response.business_card.verified_card_response.FetchActivatedBusinessCards
import app.u_business.databinding.ItemSmallBusinessCardBinding
import app.u_business.presentation.ui.news.FullscreenCardViewHolder
import com.bumptech.glide.Glide

class BusinessCardsAdapter(
    private val data: FetchActivatedBusinessCards?,
    private val onLongClickListener: OnLongClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val DEFAULT_TYPE = 0
        private const val FULLSCREEN_TYPE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 7 == 0 && position != 0)
            FULLSCREEN_TYPE
        else
            DEFAULT_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            DEFAULT_TYPE -> SmallBusinessCardsViewHolder.create(parent)
            else -> FullscreenCardViewHolder.create(parent)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            DEFAULT_TYPE -> {
                data?.get(position)
                    ?.let { (holder as SmallBusinessCardsViewHolder).bind(it, onLongClickListener) }
            }
            FULLSCREEN_TYPE -> {
                val item = data?.get(position)
                val title = "Скидка ${item?.status}% на ${item?.company}"
                val date = "до ${item?.status}"
                item?.company?.let { (holder as FullscreenCardViewHolder).bind(it, title, date) }
            }
        }
    }

    override fun getItemCount(): Int = data?.size!!
}

class SmallBusinessCardsViewHolder(
    private val binding: ItemSmallBusinessCardBinding
) : RecyclerView.ViewHolder(binding.root) {
    private var isFocused = false

    companion object {
        fun create(parent: ViewGroup): SmallBusinessCardsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSmallBusinessCardBinding.inflate(inflater, parent, false)
            return SmallBusinessCardsViewHolder(binding)
        }
    }

    fun bind(cards: ActivatedBusinessCardsItem, onLongClickListener: OnLongClickListener) {
        with(binding) {
            Glide.with(root)
//                .load(news.image)
                .load(Uri.parse("${ApiUtils.baseUrl}/${cards.avatarurl}"))
                .circleCrop()
                .into(businessCardProfileImage)
            itemSmallProfileNameText.text = cards.user
            itemSmallCompanyNameText.text = cards.company
            itemSmallIndustryText.text = cards.industry
            binding.root.setOnClickListener {
                isFocused = !isFocused
                when (isFocused) {
                    true -> it.setBackgroundColor(it.resources.getColor(R.color.orange))
                    false -> it.setBackgroundColor(it.resources.getColor(R.color.bridal_heath))
                }
            }
            binding.root.setOnLongClickListener {
                onLongClickListener.onItemLongClickListener(cards)
                return@setOnLongClickListener true
            }
        }
    }
}

