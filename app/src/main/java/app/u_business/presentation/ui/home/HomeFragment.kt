package app.u_business.presentation.ui.home

import app.u_business.R
import app.u_business.databinding.FrHomeBinding
import app.u_business.domain.model.Event
import app.u_business.domain.model.News
import app.u_business.domain.model.Offer
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.ui.profile.special_offers.SpecialOffersAdapter

class HomeFragment(override val layoutId: Int = R.layout.fr_home) : BaseFragment<FrHomeBinding>() {
    private val eventAdapter: EventAdapter by lazy { EventAdapter() }
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter() }
    private val offersAdapter: SpecialOffersAdapter by lazy { SpecialOffersAdapter() }

    override fun initViews() {
        initRecyclers()
        mockRecyclers()
    }

    private fun initRecyclers() {
        with(binding) {
            homeEventsRv.adapter = eventAdapter
            homeNewsRv.adapter = newsAdapter
            homeOffersRv.adapter = offersAdapter
        }
    }

    private fun mockRecyclers() {
        eventAdapter.data = listOf(
            Event(
                "",
                "Seo – продвижение бизнесса",
                "18 февраля 19:00"
            ),
            Event(
                "",
                "Seo – продвижение бизнесса",
                "18 февраля 19:00"
            ),
            Event(
                "",
                "Seo – продвижение бизнесса",
                "18 февраля 19:00"
            ),
            null
        )
        newsAdapter.data = listOf(
            News(
                "",
                "Мы принимаем участие в научном исследовании",
                "18 февраля 19:00",
                ""
            ),
            News(
                "",
                "Мы принимаем участие в научном исследовании",
                "18 февраля 19:00",
                ""
            ),
            News(
                "",
                "Мы принимаем участие в научном исследовании",
                "18 февраля 19:00",
                ""
            ),
            null
        )
        offersAdapter.data = listOf(
            Offer(
                10,
                "все арома масла"
            ),
            Offer(
                10,
                "все арома масла"
            ),
            Offer(
                10,
                "все арома масла"
            ),
            null
        )
    }
}