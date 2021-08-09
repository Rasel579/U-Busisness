package app.u_business.presentation.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import app.u_business.R

class BaseAdapter<M, BindingItem : ViewDataBinding>(
    private val inflate: (layoutInflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean) -> BindingItem,
    private val bindFunction: ((Binding: BindingItem, model: M) -> Unit)
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val DEFAULT_TYPE = 0
        private const val PLACEHOLDER_TYPE = 1
        private const val FULLSCREEN_TYPE = 2
    }

    private var withBannerTest = false

    fun setWithBannerTest(): BaseAdapter<M, BindingItem> {
        withBannerTest = true
        return this
    }

    var data: List<M?> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int =
        if (data[position] == null) PLACEHOLDER_TYPE
        else {
            if (position == 1 && withBannerTest) FULLSCREEN_TYPE else DEFAULT_TYPE
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            DEFAULT_TYPE -> {
                ModelViewHolder(
                    inflate(LayoutInflater.from(parent.context), parent, false), bindFunction
                )
            }
            FULLSCREEN_TYPE -> FullscreenCardViewHolder.create(parent)
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_news_more, parent, false)
                ItemsMoreViewHolder(view)
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            DEFAULT_TYPE -> (holder as ModelViewHolder<M, BindingItem>).bind(data[position]!!)
            FULLSCREEN_TYPE -> {
                val AdModel = data[position]
                (holder as FullscreenCardViewHolder).bind("Реклама", "Текст рекламы ", "Категория")
            }
        }
    }

    override fun getItemCount(): Int = data.size

    inner class ModelViewHolder<M, BindingItem : ViewDataBinding>(
        private val binding1: BindingItem,
        private val bindFunction: ((Binding: BindingItem, model: M) -> Unit)
    ) : RecyclerView.ViewHolder(binding1.root) {

        fun bind(model: M) {
            bindFunction(binding1, model)
        }
    }
}


class ItemsMoreViewHolder(v: View) : RecyclerView.ViewHolder(v)