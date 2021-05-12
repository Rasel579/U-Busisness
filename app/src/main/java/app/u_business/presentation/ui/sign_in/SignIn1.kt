package app.u_business.presentation.ui.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import app.u_business.R
import app.u_business.databinding.FragmentSignIn1Binding
import app.u_business.presentation.ui.base.BaseFragment

class SignIn1(override val layoutId: Int = R.layout.fragment_sign_in_1) : BaseFragment<FragmentSignIn1Binding>() {

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initListeners()
//    }

//    private fun initListeners() {
//        with(binding) {
//            btnSignIn.setOnClickListener { }
//            btnSignUp.setOnClickListener {  }
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Inflate the layout for this fragment
//        binding = FragmentSignIn1Binding.inflate(layoutInflater)
//
//        val navHostFragment =
//            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        binding.btnSignIn.setOnClickListener{
//            navController.navigate(SignIn1Directions.actionSignIn1ToSignIn2())
//        }
//
//        binding.btnSignUp.setOnClickListener {
//            navController.navigate(SignIn1Directions.actionSignIn1ToSignUp1())
//        }
//
//
//        return binding.root
//    }
}