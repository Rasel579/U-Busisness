package app.u_business.presentation.ui.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import app.u_business.R
import app.u_business.databinding.FrWelcomePagerBinding
import app.u_business.presentation.ui.base.BaseFragment

class WelcomeFragmentPager(override val layoutId: Int = R.layout.fr_welcome_pager) : BaseFragment<FrWelcomePagerBinding>() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        with(binding) {
            welcomeViewPager.apply {
                adapter = pagerAdapter
                addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }

                    override fun onPageSelected(position: Int) {
//                        welcomePageIndicatorView.selection = position
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                    }
                })
            }
            pagerIndicator.initWithViewPager(welcomeViewPager)
        }
    }
}