package app.u_business.presentation.ui.sign_in

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import app.u_business.R
import app.u_business.databinding.FrRegistrationBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.navigate
import app.u_business.presentation.utils.showAlertDialog
import app.u_business.presentation.utils.validateEmail
import java.util.ArrayList

class SignUp1(override val layoutId: Int = R.layout.fr_registration) : BaseFragment<FrRegistrationBinding>(){
    override fun initViews() {
         initListener()
    }

    private fun initListener()= with(binding) {
        binding.btnBack.setOnClickListener{findNavController().popBackStack()}
        binding.btnContinue.setOnClickListener{
            if(editTextName.text.isNotBlank() && editTextMail.text.isNotBlank()){
                if (editTextMail.validateEmail(editTextMail.text.toString())){
                    val userContacts = arrayListOf( editTextMail.text.toString(), editTextName.text.toString())
                    val bundle = Bundle().apply {
                        putStringArrayList(BUNDLE_USER,userContacts)
                    }
                    navigate(R.id.action_signUp1_to_signUp2, bundle)
                } else{
                    showAlertDialog(R.string.alert_title_not_valid_email, R.string.alert_message_not_valid_email)
                }
            } else {
                showAlertDialog(R.string.alert_title_name_email,R.string.alert_message_empty_name_email_message)
            }

        }
    }

    companion object {
        const val BUNDLE_USER = "user_contacts"
    }
}