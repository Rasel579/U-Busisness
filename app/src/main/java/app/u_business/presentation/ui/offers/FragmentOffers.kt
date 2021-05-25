package app.u_business.presentation.ui.offers

import app.u_business.R
import app.u_business.databinding.FrOffersBinding
import app.u_business.domain.model.Offer
import app.u_business.presentation.ui.base.BaseFragment

class FragmentOffers(override val layoutId: Int = R.layout.fr_offers) :
    BaseFragment<FrOffersBinding>() {

    private val adapter: OffersAdapter by lazy { OffersAdapter() }

    override fun initViews() {
        initRv()
        mockAdapter()
    }

    private fun initRv() {
        binding.offersRv.adapter = adapter
    }

    private fun mockAdapter() {
        adapter.data = listOf(
            Offer(10, "все арома масла", "18 октября", ""),
            Offer(10, "всю обувь", "18 октября", ""),
            Offer(10, "стройматериалы", "18 октября", ""),
            Offer(10, "все мастерклассы", "18 октября", ""),
            Offer(10, "все арома масла", "18 октября", ""),
            Offer(10, "всю обувь", "18 октября", ""),
            Offer(10, "стройматериалы", "18 октября", ""),
            Offer(10, "все мастерклассы", "18 октября", ""),
        )
    }
}