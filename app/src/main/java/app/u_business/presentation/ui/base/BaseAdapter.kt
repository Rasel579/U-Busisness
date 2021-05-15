package app.u_business.presentation.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<Binding: ViewDataBinding, DataClass : Any> : RecyclerView.Adapter<BaseViewHolder<DataClass>>() {
    protected abstract val layoutId: Int

    var data: List<DataClass> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DataClass> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<Binding>(inflater, layoutId, parent, false)
        return getVH(binding)
    }

    abstract fun getVH(binding: Binding): BaseViewHolder<DataClass>

    override fun onBindViewHolder(holder: BaseViewHolder<DataClass>, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

}