package app.u_business.presentation.ui.profile

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.u_business.domain.model.PaymentHistory

class PaymentHistoryAdapter : RecyclerView.Adapter<PaymentHistoryViewHolder>() {
    var data: List<PaymentHistory> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHistoryViewHolder =
        PaymentHistoryViewHolder.create(parent)

    override fun onBindViewHolder(holder: PaymentHistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}