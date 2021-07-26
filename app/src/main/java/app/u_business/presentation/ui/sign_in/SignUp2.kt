package app.u_business.presentation.ui.sign_in

import android.content.Intent
import app.u_business.R
import app.u_business.databinding.FrRegistrationPasswordBinding
import app.u_business.presentation.ui.Home
import app.u_business.presentation.ui.base.BaseFragment

class SignUp2(override val layoutId: Int = R.layout.fr_registration_password) :
    BaseFragment<FrRegistrationPasswordBinding>() {
    override fun initViews() {
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(requireContext(), Home::class.java))
            requireActivity().finish()
        }
    }
}
