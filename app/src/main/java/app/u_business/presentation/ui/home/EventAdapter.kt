package app.u_business.presentation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.u_business.R
import app.u_business.databinding.ItemEventBinding
import app.u_business.domain.model.Event
import com.bumptech.glide.Glide

class EventAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val DEFAULT_TYPE = 0
        private const val PLACEHOLDER_TYPE = 1
    }

    var data: List<Event?> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int =
        /*if (data[position] == null) PLACEHOLDER_TYPE else */DEFAULT_TYPE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            DEFAULT_TYPE -> {
                EventViewHolder.create(parent)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_event_more, parent, false)
                EventMoreViewHolder(view)
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == DEFAULT_TYPE)
            (holder as EventViewHolder).bind(data[position]!!)
    }

    override fun getItemCount(): Int = data.size
}

class EventViewHolder(
    private val binding: ItemEventBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): EventViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemEventBinding.inflate(inflater, parent, false)
            return EventViewHolder(binding)
        }
    }

    fun bind(event: Event) {
        with(binding) {
            Glide.with(binding.root)
//                .load(event.image)
                .load(R.drawable.test_image)
                .fitCenter()
                .into(itemEventImage)

            itemEventTitleText.text = event.title
            itemEventDateText.text = event.date
        }
    }
}

class EventMoreViewHolder(v: View) : RecyclerView.ViewHolder(v)