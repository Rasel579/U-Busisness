package app.u_business.presentation.ui.gallery

import app.u_business.R
import app.u_business.databinding.ItemPictureBinding
import app.u_business.presentation.ui.base.BaseAdapter
import app.u_business.presentation.ui.base.BaseViewHolder
import com.bumptech.glide.Glide
import kotlin.String as ImagePath

class PicturesAdapter(override val layoutId: Int = R.layout.item_picture) :
    BaseAdapter<ItemPictureBinding, ImagePath>() {
    override fun getVH(binding: ItemPictureBinding): BaseViewHolder<ImagePath> =
        PictureViewHolder(binding)
}

class PictureViewHolder(
    private val binding: ItemPictureBinding
) : BaseViewHolder<ImagePath>(binding) {

    override fun bind(image: ImagePath) {
        with(binding) {
            Glide.with(root.context)
//                .load(image)
                .load(R.drawable.test_image)
                .centerCrop()
                .into(itemPictureImage)
        }
    }
}