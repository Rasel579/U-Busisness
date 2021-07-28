package app.u_business.presentation.ui.sign_in

import android.os.Bundle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import app.u_business.R
import app.u_business.data.network.query.user.RegistrationBody
import app.u_business.databinding.FrAuthBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.navigate


class SignIn1(override val layoutId: Int = R.layout.fr_auth) : BaseFragment<FrAuthBinding>() {
    override fun initViews()= with(binding) {
        btnSignUp.setOnClickListener{navigate(R.id.action_signIn1_to_signUp1)}
        btnSignIn.setOnClickListener{navigate(R.id.action_auth_to_signIn2)}
    }


    }


