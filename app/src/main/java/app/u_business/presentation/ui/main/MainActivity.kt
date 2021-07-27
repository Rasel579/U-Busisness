package app.u_business.presentation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import app.u_business.R
import app.u_business.presentation.ui.Home
import app.u_business.presentation.utils.SharedPreferencesHelper
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivity : AppCompatActivity(), KoinComponent{
    private val vm by viewModel<MainVM>()
    private val sharedPreferencesHelper by inject<SharedPreferencesHelper>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main)

        vm.navEvents.observe(this){ event ->
            when(event.action){
                NavigateEventAction.ONBOARDING -> {
                    navigateRoot(R.id.welcomeFragmentPager)
                    sharedPreferencesHelper.isFirstOpen = false
                }
              //  NavigateEventAction.ONBOARDING -> startActivity(Intent(this, ::class.java))
                NavigateEventAction.AUTH -> navigateRoot(R.id.auth)
                NavigateEventAction.HOME -> startActivity(Intent(this, Home::class.java))
            }
        }
    }

    private fun navigateRoot(id: Int) {
        val findNavController = Navigation.findNavController(this, R.id.nav_main_fragment)
        findNavController.let { controller ->
            val nav =
                controller.navInflater.inflate(R.navigation.auth_navigation)
            nav.startDestination = id
            controller.graph = nav
        }
    }
}