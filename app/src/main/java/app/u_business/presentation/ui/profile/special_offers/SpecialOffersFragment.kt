package app.u_business.presentation.ui.profile.special_offers

import app.u_business.R
import app.u_business.databinding.FrSpecialOffersBinding
import app.u_business.domain.model.Offer
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.navigate

class SpecialOffersFragment(override val layoutId: Int = R.layout.fr_special_offers) :
    BaseFragment<FrSpecialOffersBinding>() {

    private val adapter: SpecialOffersAdapter by lazy {
        SpecialOffersAdapter {
            openEditOffer()
        }
    }

    override fun initViews() {
        initRecyclerView()
    }

    private fun openEditOffer() {
        navigate(R.id.nav_edit_offer)
    }

    private fun initRecyclerView() {
        binding.specialOffersRv.adapter = adapter
        mockAdapter()
    }

    private fun mockAdapter() {
        adapter.data = listOf(
            Offer(10, "все арома масла"),
            Offer(10, "всю обувь"),
            Offer(10, "стройматериалы"),
            Offer(10, "все мастерклассы"),
        )
    }
}