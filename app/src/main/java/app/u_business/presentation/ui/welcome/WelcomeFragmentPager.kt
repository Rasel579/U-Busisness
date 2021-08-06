package app.u_business.presentation.ui.welcome

import android.os.Bundle
import app.u_business.R
import app.u_business.databinding.FrWelcomePagerBinding
import app.u_business.presentation.ui.base.BaseFragment

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