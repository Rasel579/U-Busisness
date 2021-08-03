package app.u_business.presentation.ui.sign_in

import app.u_business.R
import app.u_business.databinding.FrAuthBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.navigate


class AuthFragment(override val layoutId: Int = R.layout.fr_auth) : BaseFragment<FrAuthBinding>() {

    override fun initViews() {
        binding.btnSignIn.setOnClickListener { navigate(AuthFragmentDirections.actionAuthToSignIn2()) }
        binding.btnSignUp.setOnClickListener { navigate(AuthFragmentDirections.actionSignIn1ToSignUp1()) }
    }
}
