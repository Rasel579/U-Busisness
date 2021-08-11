package app.u_business.presentation.ui.sign_in

import android.content.Intent
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import app.u_business.R
import app.u_business.data.network.query.user.LoginBody
import app.u_business.databinding.FrLoginBinding
import app.u_business.presentation.ui.Home
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.SharedPreferencesHelper
import app.u_business.presentation.utils.navigate
import app.u_business.presentation.utils.showAlertDialog
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment(override val layoutId: Int = R.layout.fr_login) :
    BaseFragment<FrLoginBinding>() {
    private val vm by viewModel<AuthVM>()
    private val sharedPreferencesHelper by inject<SharedPreferencesHelper>()
    override fun initViews() {
        initListeners()
        initEventAction()
    }

    private fun initListeners() = with(binding) {
        btnBack.setOnClickListener { findNavController().popBackStack() }
        textViewForgotPassword.setOnClickListener { navigate(SignInFragmentDirections.actionSignIn2ToForgotPassword()) }
        btnSignIn.setOnClickListener {
            if (editTextMail.text.isNotBlank() && editTextPassword.text.isNotBlank()) {
                vm.signIn(LoginBody(editTextMail.text.toString(), editTextPassword.text.toString()))
            }
        }
    }

    private fun initEventAction() {
        vm.authEvents.observe(this) { event ->
            when (event.action) {
                is AuthEventAction.Success -> {
                    sharedPreferencesHelper.accessToken = event.action.loginResponse?.accessToken
                    sharedPreferencesHelper.isAuthed = true
                    sharedPreferencesHelper.registrationBody =
                        Gson().toJson(event.action.loginResponse)
                    sharedPreferencesHelper.userId = event.action.loginResponse?.userId.toString()
                    startActivity(Intent(activity, Home::class.java))
                }
                is AuthEventAction.Error -> {
                    event.action.string?.let {
                        sharedPreferencesHelper.isAuthed = true
                        showAlertDialog(it, event.action.string)
                    }
                }
                is AuthEventAction.NotPayed -> {
                    sharedPreferencesHelper.accessToken = event.action.loginResponse?.accessToken
                    sharedPreferencesHelper.isAuthed = true
                    sharedPreferencesHelper.registrationBody =
                        Gson().toJson(event.action.loginResponse)
                    sharedPreferencesHelper.userId = event.action.loginResponse?.userId.toString()
                    navigateRoot(R.id.paymentWaitingFragment)
                }
                is AuthEventAction.Payed -> {
                    sharedPreferencesHelper.accessToken = event.action.loginResponse?.accessToken
                    sharedPreferencesHelper.isAuthed = true
                    sharedPreferencesHelper.registrationBody =
                        Gson().toJson(event.action.loginResponse)
                    sharedPreferencesHelper.userId = event.action.loginResponse?.userId.toString()
                    startActivity(Intent(activity, Home::class.java))
                }

//                is AuthEventAction.SuccessRecovery -> TODO()
            }
        }
    }

    private fun navigateRoot(id: Int) {
        val findNavController =
            Navigation.findNavController(requireActivity(), R.id.nav_main_fragment)
        findNavController.let { controller ->
            val nav =
                controller.navInflater.inflate(R.navigation.auth_navigation)
            nav.startDestination = id
            controller.graph = nav
        }
    }
}