package app.u_business.presentation.ui.home

import android.view.View
import app.u_business.R
import app.u_business.databinding.FrHomeBinding
import app.u_business.domain.model.Offer
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.ui.news.renderData
import app.u_business.presentation.ui.profile.special_offers.SpecialOffersAdapter
import app.u_business.presentation.utils.SharedPreferencesHelper
import app.u_business.presentation.utils.navigate
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment(override val layoutId: Int = R.layout.fr_home) : BaseFragment<FrHomeBinding>() {
    private val eventAdapter: EventAdapter by lazy { EventAdapter() }
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter() }
    private val offersAdapter: SpecialOffersAdapter by lazy { SpecialOffersAdapter() }

    private val prefs by inject<SharedPreferencesHelper>()
    private val vm by viewModel<HomeVM>()
    override fun initViews() {
        initRecyclers()
        mockRecyclers()

        vm.state.observe(this) {
            renderData(it, null, {
                newsAdapter.data = it.data.news.toNewsWithEmpty(2)
                eventAdapter.data = it.data.events.map { it.toEven() }
            })
        }
        vm.requestNewsList()
    }

    private fun initRecyclers() {
        with(binding) {
            homeEventsRv.adapter = eventAdapter
            homeNewsRv.adapter = newsAdapter
            homeOffersRv.adapter = offersAdapter
            homeRegisterBtn.apply {
                visibility = if (prefs.isAuthed) View.GONE else View.VISIBLE
                setOnClickListener { navigate(R.id.sign_up) }
            }
        }
    }

    private fun mockRecyclers() {
        offersAdapter.data = listOf(
            Offer(
                10,
                "все арома масла",
                "",
                ""
            ),
            Offer(
                10,
                "все арома масла",
                "",
                ""
            ),
            Offer(
                10,
                "все арома масла",
                "",
                ""
            ),
            null
        )
    }
}