package app.u_business.presentation.ui.profile

import android.os.Bundle
import app.u_business.R
import app.u_business.databinding.FrProflePagerBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.ui.profile.account.AccountFragment
import app.u_business.presentation.ui.profile.business_card.BusinessCardFragment
import app.u_business.presentation.ui.profile.special_offers.SpecialOffersFragment
import app.u_business.presentation.ui.welcome.*

class ProfilePagerFragment(override val layoutId: Int = R.layout.fr_profle_pager) :
    BaseFragment<FrProflePagerBinding>() {

    private lateinit var pagerAdapter: ProfilePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pagerAdapter = ProfilePagerAdapter(
            requireContext(),
            listOf(
                AccountFragment(),
                BusinessCardFragment(),
                SpecialOffersFragment()
            ),
            childFragmentManager
        )
    }

    override fun initViews() {
        initPager()
    }

    private fun initPager() {
        binding.profileViewPager.adapter = pagerAdapter
    }
}