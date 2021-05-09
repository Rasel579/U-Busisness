package app.u_business.presentation.ui.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import app.u_business.R
import app.u_business.databinding.FrWelcomePagerBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class WelcomeFragmentPager : Fragment(R.layout.fr_welcome_pager) {
    private val binding: FrWelcomePagerBinding by viewBinding()
    private lateinit var pagerAdapter: WelcomePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pagerAdapter = WelcomePagerAdapter(requireContext(), childFragmentManager)
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
                        welcomePageIndicatorView.selection = position
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                    }
                })
            }
        }
    }
}