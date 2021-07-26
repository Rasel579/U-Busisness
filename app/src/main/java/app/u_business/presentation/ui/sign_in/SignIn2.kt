package app.u_business.presentation.ui.sign_in

import android.content.Intent
import app.u_business.R
import app.u_business.databinding.FrLoginBinding
import app.u_business.presentation.ui.Home
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.navigate

class SignIn2(override val layoutId: Int = R.layout.fr_login) : BaseFragment<FrLoginBinding>() {
    override fun initViews() {
        binding.btnSignIn.setOnClickListener { startActivity(Intent(requireContext(), Home::class.java))
        requireActivity().finish()}
    }
}
