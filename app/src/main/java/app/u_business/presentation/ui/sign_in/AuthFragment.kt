package app.u_business.presentation.ui.sign_in

import android.os.Bundle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import app.u_business.R
import app.u_business.data.network.query.user.RegistrationBody
import app.u_business.databinding.FrAuthBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.navigate

import app.u_business.presentation.utils.SharedPreferencesHelper
import app.u_business.presentation.utils.navigate
import org.koin.android.ext.android.inject


class AuthFragment(override val layoutId: Int = R.layout.fr_auth) : BaseFragment<FrAuthBinding>() {

    override fun initViews() {
        binding.btnSignIn.setOnClickListener { navigate(AuthFragmentDirections.actionAuthToSignIn2()) }
        binding.btnSignUp.setOnClickListener { navigate(AuthFragmentDirections.actionSignIn1ToSignUp1()) }
    }
}
