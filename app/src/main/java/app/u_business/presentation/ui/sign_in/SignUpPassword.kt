package app.u_business.presentation.ui.sign_in

import android.content.Intent
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import app.u_business.R
import app.u_business.data.network.query.user.RegistrationBody
import app.u_business.databinding.FrRegistrationPasswordBinding
import app.u_business.presentation.ui.Home
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.SharedPreferencesHelper
import app.u_business.presentation.utils.navigate
import app.u_business.presentation.utils.showAlertDialog
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpPassword(override val layoutId: Int = R.layout.fr_registration_password) : BaseFragment<FrRegistrationPasswordBinding>() {
    private val vm by viewModel<AuthVM>()
    private val sharePref by inject<SharedPreferencesHelper>()
    override fun initViews() {
        initListener()
        initEventAction()
    }

    private fun initListener() = with(binding) {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnSignUp.setOnClickListener {
            if (!editTextPassword.text.isNullOrBlank() && !editTextRepeatPassword.text.isNullOrBlank()
                && editTextPassword.text.toString() == editTextRepeatPassword.text.toString()
            ) {
                val contacts = arguments?.getStringArrayList(SignUp.BUNDLE_USER)
                val name = contacts?.get(0)
                val email = contacts?.get(1)
                val password = editTextRepeatPassword.text.toString()
                vm.registerUser(RegistrationBody(email, name, password))
            } else {
                showAlertDialog(
                    R.string.alert_title_password_not_equals,
                    R.string.alert_title_password_not_equals
                )
            }
        }
    }

    private fun initEventAction() {
        vm.authEvents.observe(this) { event ->
            when (event.action) {
                is AuthEventAction.Success -> {
                    sharePref.accessToken = event.action.loginResponse?.accessToken
                    sharePref.isAuthed = true
                    sharePref.registrationBody = Gson().toJson(event.action.loginResponse)
                    sharePref.userId = event.action.loginResponse?.userId.toString()
                    navigateRoot(R.id.paymentWaitingFragment)
//                    startActivity(Intent(activity, Home::class.java))
                }
                is AuthEventAction.Error -> {
                    event.action.string?.let { showAlertDialog(it, event.action.string,) }
                }
            }
        }
    }

    private fun navigateRoot(id: Int) {
        val findNavController = Navigation.findNavController(requireActivity(), R.id.nav_main_fragment)
        findNavController.let { controller ->
            val nav =
                controller.navInflater.inflate(R.navigation.auth_navigation)
            nav.startDestination = id
            controller.graph = nav
        }
    }
}