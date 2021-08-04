package app.u_business.presentation.ui.eventlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.u_business.R
import app.u_business.databinding.ItemEventForListBinding
import app.u_business.databinding.ItemSmallEventNewsRBinding
import app.u_business.domain.model.EventList
import app.u_business.presentation.ui.eventlist.`interface`.OnItemEventClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class EventListAdapter(
    private val itemEventClickListener: OnItemEventClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val DEFAULT_TYPE = 0
        private const val PLACEHOLDER_TYPE = 1
    }

    var data: List<EventList?> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setEventListData(dataEventList: List<EventList>) {
        data = dataEventList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int =
        if (data[position] == null) PLACEHOLDER_TYPE else DEFAULT_TYPE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            DEFAULT_TYPE -> {
                EventListViewHolder.create(parent)
            }
            else -> {
//                val inflater = LayoutInflater.from(parent.context)
//                val view = inflater.inflate(R.layout.item_small_event_news_r, parent, false)
                EventListItemViewHolder.create(parent)
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == DEFAULT_TYPE)
            (holder as EventListViewHolder).bind(data[position]!!)

        holder.itemView.setOnClickListener {
            itemEventClickListener.onItemViewClick(data[position]!!)
        }
    }

    override fun getItemCount(): Int = data.size
}

class EventListViewHolder(
    private val binding: ItemEventForListBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): EventListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemEventForListBinding.inflate(inflater, parent, false)
            return EventListViewHolder(binding)
        }
    }

    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_no_photo_vector)
        .fallback(R.drawable.ic_no_photo_vector)
        .centerCrop()

    fun bind(eventList: EventList) {
        with(binding) {
            Glide.with(binding.root)
                .load(eventList.image)
                .fitCenter()
                .apply(imageOption)
                .into(itemEventImage)

            itemEventTitleText.text = eventList.title
            itemEventDateText.text = eventList.date
            itemEventTitleTypeText.text = eventList.category
        }
    }
}

//class EventMoreViewHolder(v: View) : RecyclerView.ViewHolder(v)

class EventListItemViewHolder(
    private val binding: ItemSmallEventNewsRBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): EventListItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSmallEventNewsRBinding.inflate(inflater, parent, false)
            return EventListItemViewHolder(binding)
        }
    }

    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_no_photo_vector)
        .fallback(R.drawable.ic_no_photo_vector)
        .centerCrop()

    fun bind(eventListItem: EventList) {
        with(binding) {
            Glide.with(binding.root)
                .load(eventListItem.image)
                .fitCenter()
                .apply(imageOption)
                .into(itemSmallEventNewsImage)

            itemSmallNewsTypeText.text = eventListItem.category
            itemSmallNewsTitleText.text = eventListItem.title
            itemSmallNewsDateText.text = eventListItem.date
        }
    }
}