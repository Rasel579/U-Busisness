package app.u_business.presentation.ui.sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import app.u_business.R
import app.u_business.databinding.FragmentSignIn2Binding

class SignIn2 : Fragment() {
    private lateinit var binding: FragmentSignIn2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentSignIn2Binding.inflate(layoutInflater)

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.btnBack.setOnClickListener {
            navController.popBackStack()
        }

        binding.textViewForgotPassword.setOnClickListener {
            navController.navigate(SignIn2Directions.actionSignIn2ToForgotPassword())
        }

//        binding.btnSignIn.setOnClickListener {
//            if(binding.editTextMail.)
//        }

        return binding.root
    }
}