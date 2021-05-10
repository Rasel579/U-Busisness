package app.u_business.presentation.ui.profile

import androidx.fragment.app.Fragment
import app.u_business.R
import app.u_business.databinding.FrSubscriptionBinding
import app.u_business.domain.model.PaymentHistory
import app.u_business.presentation.ui.base.BaseFragment

class SubscriptionFragment(override val layoutId: Int = R.layout.fr_subscription) :
    BaseFragment<FrSubscriptionBinding>() {

    private val adapter: PaymentHistoryAdapter by lazy { PaymentHistoryAdapter() }

    override fun initViews() {
        initAdapter()
        mockAdapter()
    }

    private fun initAdapter() {
        binding.subscriptionPaymentsRv.adapter = adapter
    }

    private fun mockAdapter() {
        adapter.data = listOf(
            PaymentHistory(
                "Продление премиум-доступа",
                "5 февраля 2021",
                "—350₽"
            ),
            PaymentHistory(
                "Продление премиум-доступа",
                "5 февраля 2021",
                "—350₽"
            ),

        )
    }
}