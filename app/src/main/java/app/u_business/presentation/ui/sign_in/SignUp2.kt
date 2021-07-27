package app.u_business.presentation.ui.sign_in

import android.util.Log
import app.u_business.R
import app.u_business.databinding.FrRegistrationPasswordBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.showAlertDialog

class SignUp2(override val layoutId: Int = R.layout.fr_registration_password) : BaseFragment<FrRegistrationPasswordBinding>() {
    override fun initViews() {
       initListener()
    }

    private fun initListener()= with(binding) {
        binding.btnSignUp.setOnClickListener{
            if (!editTextPassword.text.isNullOrBlank() && !editTextRepeatPassword.text.isNullOrBlank()
                && editTextPassword.text.toString() == editTextRepeatPassword.text.toString()
            ) {
                Log.e("win", "win ")
                }
            else {
                showAlertDialog(R.string.alert_title_password_not_equals,
                             R.string.alert_title_password_not_equals)
            }
        }
    }
}
