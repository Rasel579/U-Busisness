package app.u_business.presentation.ui.sign_in

import androidx.navigation.fragment.findNavController
import app.u_business.R
import app.u_business.databinding.FragmentForgotPasswordBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.showAlertDialog
import app.u_business.presentation.utils.validateEmail
import org.koin.android.ext.android.inject

class ForgotPassword(override val layoutId: Int = R.layout.fragment_forgot_password) : BaseFragment<FragmentForgotPasswordBinding>() {
    val vm by inject<AuthVM>()
    override fun initViews()= with(binding) {
        initEvents()
        btnBack.setOnClickListener{findNavController().popBackStack()}
        btnSignUp.setOnClickListener{
            if (editTextMail.validateEmail(editTextMail.text.toString())){
                vm.recovery(editTextMail.text.toString())
            }
        }
    }

    private fun initEvents() {
        vm.authEvents.observe(viewLifecycleOwner){ event ->
            when(event.action){
                is AuthEventAction.SuccessRecovery -> {
                    showAlertDialog(event.action.message, event.action.message)
                }
                is AuthEventAction.Error -> {
                    event.action.string?.let { showAlertDialog(it, event.action.string) }
                }
            }

        }
    }
}
