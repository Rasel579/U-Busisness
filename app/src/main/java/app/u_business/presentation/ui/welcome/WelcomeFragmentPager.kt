package app.u_business.presentation.ui.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import app.u_business.R
import app.u_business.databinding.FrAuthBinding
import app.u_business.databinding.FrWelcomePagerBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.ui.sign_in.SignIn1

class WelcomeFragmentPager(override val layoutId: Int = R.layout.fr_welcome_pager) :
    BaseFragment<FrWelcomePagerBinding>() {

    private lateinit var pagerAdapter: WelcomePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pagerAdapter = WelcomePagerAdapter(
            requireContext(),
            listOf(
                WelcomeFragment(),
                FirstOnBoardFragment(),
                SecondOnBoardFragment(),
                ThirdOnBoardFragment()
            ),
            childFragmentManager
        )
    }

    override fun initViews() {
        initViewPager()
    }

    private fun initViewPager() {
        with(binding) {
            welcomeViewPager.adapter = pagerAdapter
            pagerIndicator.initWithViewPager(welcomeViewPager)
        }
    }
}