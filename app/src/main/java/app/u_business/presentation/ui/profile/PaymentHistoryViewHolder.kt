package app.u_business.presentation.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.u_business.databinding.ItemPaymentHistoryBinding
import app.u_business.domain.model.PaymentHistory

class PaymentHistoryViewHolder(
    private val binding: ItemPaymentHistoryBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): PaymentHistoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemPaymentHistoryBinding.inflate(inflater, parent, false)
            return PaymentHistoryViewHolder(binding)
        }
    }

    fun bind(paymentHistory: PaymentHistory) {
        with(binding) {
            itemPaymentTypeText.text = paymentHistory.type
            itemPaymentDateText.text = paymentHistory.date
            itemPaymentSumText.text = paymentHistory.sum
        }
    }
}