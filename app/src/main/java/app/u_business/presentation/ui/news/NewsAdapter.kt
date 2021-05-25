package app.u_business.presentation.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.u_business.R
import app.u_business.databinding.ItemSmallNewsBinding
import app.u_business.domain.model.News
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val DEFAULT_TYPE = 0
        private const val FULLSCREEN_TYPE = 1
    }

    var data: List<News> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return if (position % 7 == 0)
            FULLSCREEN_TYPE
        else
            DEFAULT_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType) {
            DEFAULT_TYPE -> SmallNewsViewHolder.create(parent)
            else -> FullscreenCardViewHolder.create(parent)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            DEFAULT_TYPE -> { (holder as SmallNewsViewHolder).bind(data[position]) }
            FULLSCREEN_TYPE -> {
                val news = data[position]
                (holder as FullscreenCardViewHolder).bind(news.image, news.title, news.date)
            }
        }
    }

    override fun getItemCount(): Int = data.size
}

class SmallNewsViewHolder(
    private val binding: ItemSmallNewsBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): SmallNewsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSmallNewsBinding.inflate(inflater, parent, false)
            return SmallNewsViewHolder(binding)
        }
    }

    fun bind(news: News) {
        with(binding) {
            Glide.with(root)
//                .load(news.image)
                .load(R.drawable.test_image)
                .circleCrop()
                .into(itemSmallNewsImage)

            itemSmallNewsTitleText.text = news.title
            itemSmallNewsDateText.text = news.date
            itemSmallNewsContentText.text = news.content
        }
    }
}