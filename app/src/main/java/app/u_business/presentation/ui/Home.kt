package app.u_business.presentation.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import app.u_business.R
import app.u_business.databinding.AcHomeBinding
import app.u_business.presentation.utils.SharedPreferencesHelper
import com.google.android.material.navigation.NavigationView
import org.koin.android.ext.android.inject


class Home : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: AcHomeBinding
    private lateinit var navController: NavController
    private val sharedPref by inject<SharedPreferencesHelper>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AcHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarHome.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        drawerLayout.setScrimColor(resources.getColor(android.R.color.transparent))
        drawerLayout.drawerElevation = 0f
        navController = findNavController(R.id.nav_host_fragment_content_home)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_profile,
                R.id.nav_business_cards,
                R.id.nav_calendar,
                R.id.nav_offers,
                R.id.nav_news,
                R.id.nav_library,
                R.id.nav_gallery,
                R.id.nav_about_us,
                R.id.nav_connect_with_us,
                R.id.nav_home,
            ), drawerLayout
        )

        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)
        binding.navView.itemIconTintList = null
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
//            supportActionBar?.apply {
//                setHomeAsUpIndicator(if (destination.id != R.id.nav_home) R.drawable.ic_back else R.drawable.ic_arrow_down)
//            }
//        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (navController.currentDestination?.id != R.id.nav_home)
                    navController.navigate(R.id.nav_home)
                else
                    binding.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
            R.id.profile -> {
                navController.navigate(R.id.nav_profile)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}