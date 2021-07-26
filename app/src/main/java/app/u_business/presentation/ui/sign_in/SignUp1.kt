package app.u_business.presentation.ui.sign_in

import app.u_business.R
import app.u_business.databinding.FrRegistrationBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.navigate

class SignUp1(override val layoutId: Int = R.layout.fr_registration) : BaseFragment<FrRegistrationBinding>(){

    override fun initViews() {
        binding.btnContinue.setOnClickListener { navigate(R.id.signUp2) }
    }
}