package app.u_business.presentation.ui.library

import androidx.core.content.ContextCompat
import app.u_business.R
import app.u_business.databinding.ItemFileBinding
import app.u_business.domain.model.File
import app.u_business.presentation.ui.base.BaseAdapter
import app.u_business.presentation.ui.base.BaseViewHolder
import com.bumptech.glide.Glide

class FilesAdapter(override val layoutId: Int = R.layout.item_file) :
    BaseAdapter<ItemFileBinding, File>() {

    override fun getVH(binding: ItemFileBinding): BaseViewHolder<File> = FileViewHolder(binding)
}

class FileViewHolder(
    private val binding: ItemFileBinding
) : BaseViewHolder<File>(binding) {

    override fun bind(data: File) {
        with(binding) {
            fun setIcon(iconId: Int) {
                Glide.with(root)
                    .load(iconId)
                    .into(itemFileImage)
            }

            when (data.folder) {
                Folder.DOCUMENTS -> setIcon(R.drawable.ic_document)
                Folder.PHOTOS -> setIcon(R.drawable.ic_picture)
                Folder.VIDEOS -> setIcon(R.drawable.ic_film)
                Folder.RECORDINGS -> setIcon(R.drawable.ic_music)
            }
            itemFileImage.backgroundTintList =
                ContextCompat.getColorStateList(root.context, data.folder.bgTintId)
            itemFileNameText.text = data.name
            size = data.size
            executePendingBindings()
        }
    }
}