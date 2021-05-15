package app.u_business.presentation.ui.base

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<DataClass : Any>(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(data: DataClass) {

    }
}