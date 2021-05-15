package app.u_business.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import app.u_business.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent{
    private val vm by viewModel<MainVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main)
        vm.navEvents.observe(this){ event ->
            when(event.action){
                NavigateEventAction.ONBOARDING -> navigateRoot(R.id.welcomeFragmentPager)
                NavigateEventAction.AUTH -> navigateRoot(R.id.auth)
                NavigateEventAction.HOME -> navigateRoot(R.id.homeFragment)
            }
        }
    }

    private fun navigateRoot(id: Int) {
        val findNavController = Navigation.findNavController(this, R.id.nav_main_fragment)
        findNavController.let { controller ->
            val nav =
                controller.navInflater.inflate(R.navigation.main_navigation)
            nav.startDestination = id
            controller.graph = nav
        }
    }
}