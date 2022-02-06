package app.u_business.presentation.ui.sign_in

import app.u_business.R
import app.u_business.databinding.FragmentIsWaitingBinding
import app.u_business.presentation.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.net.Uri

import android.content.Intent




class PaymentWaitingFragment(override val layoutId: Int = R.layout.fragment_is_waiting) : BaseFragment<FragmentIsWaitingBinding>() {
    private val vm by viewModel<AuthVM>()
    override fun initViews() {

        vm.getPaymentLink()
        vm.authEvents.observe(this) {
            when (it.action) {
                is AuthEventAction.Link -> {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.action.link))
                    startActivity(browserIntent)
                }
                else -> {}
            }
        }
    }
}