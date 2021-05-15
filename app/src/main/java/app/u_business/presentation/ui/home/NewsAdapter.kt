package app.u_business.presentation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.u_business.R
import app.u_business.databinding.ItemNewsBinding
import app.u_business.domain.model.News
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val DEFAULT_TYPE = 0
        private const val PLACEHOLDER_TYPE = 1
    }

    var data: List<News?> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int =
        if (data[position] == null) PLACEHOLDER_TYPE else DEFAULT_TYPE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            DEFAULT_TYPE -> {
                NewsViewHolder.create(parent)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_news_more, parent, false)
                NewsMoreViewHolder(view)
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == DEFAULT_TYPE)
            (holder as NewsViewHolder).bind(data[position]!!)
    }

    override fun getItemCount(): Int = data.size
}

class NewsViewHolder(
    private val binding: ItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemNewsBinding.inflate(inflater, parent, false)
            return NewsViewHolder(binding)
        }
    }

    fun bind(news: News) {
        with(binding) {
            Glide.with(binding.root)
//                .load(news.image)
                .load(R.drawable.test_news)
                .fitCenter()
                .into(itemNewsImage)

            itemNewsTitleText.text = news.title
            itemNewsDateText.text = news.date
        }
    }
}

class NewsMoreViewHolder(v: View) : RecyclerView.ViewHolder(v)