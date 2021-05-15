package app.u_business.presentation.ui.library

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import app.u_business.R
import app.u_business.databinding.ItemFileFolderBinding
import app.u_business.presentation.ui.base.BaseAdapter
import app.u_business.presentation.ui.base.BaseViewHolder

class FoldersAdapter(override val layoutId: Int = R.layout.item_file_folder) :
    BaseAdapter<ItemFileFolderBinding, Folder>() {

    init {
        data = Folder.values().toList()
    }

    override fun getVH(binding: ItemFileFolderBinding): FolderViewHolder =
        FolderViewHolder(binding)
}

class FolderViewHolder(
    private val binding: ItemFileFolderBinding
) : BaseViewHolder<Folder>(binding) {

    override fun bind(data: Folder) {
        with(binding) {
            root.context.let { context ->
                itemFileFolderNameText.text = context.getString(data.titleId)
                itemFileFolderTypeText.text = data.types
                root.backgroundTintList =
                    ContextCompat.getColorStateList(context, data.bgTintId)
            }
        }
    }
}