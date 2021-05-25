package app.u_business.presentation.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.u_business.R
import app.u_business.databinding.ItemFullscreenCardBinding
import com.bumptech.glide.Glide

class FullscreenCardViewHolder(
    private val binding: ItemFullscreenCardBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): FullscreenCardViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemFullscreenCardBinding.inflate(inflater, parent, false)
            return FullscreenCardViewHolder(binding)
        }
    }

    fun bind(image: String, title: String, date: String, category: String? = null) {
        with(binding) {
            Glide.with(root)
//                .load(image)
                .load(R.drawable.test_image)
                .centerCrop()
                .into(itemFullscreenCardImage)

            itemFullscreenCardTitleText.text = title
            itemFullscreenCardDateText.text = date
            category?.let { itemFullscreenCardCategoryText.text = it }
        }
    }
}