package app.u_business.presentation.ui.sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import app.u_business.R
import app.u_business.databinding.FragmentSignIn1Binding
import app.u_business.databinding.FragmentSignUp1Binding
import app.u_business.presentation.ui.base.BaseFragment

class SignUp1(override val layoutId: Int = R.layout.fragment_sign_up_1) : BaseFragment<FragmentSignUp1Binding>(){

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Inflate the layout for this fragment
//        binding = FragmentSignUp1Binding.inflate(layoutInflater)
//
//        val navHostFragment =
//            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        binding.btnBack.setOnClickListener {
//            navController.popBackStack()
//        }
//
//        binding.btnContinue.setOnClickListener {
//            navController.navigate(SignUp1Directions.actionSignUp1ToSignUp2())
//        }
//        return binding.root
//    }
}
