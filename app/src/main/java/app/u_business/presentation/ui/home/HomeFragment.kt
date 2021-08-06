package app.u_business.presentation.ui.home

import android.util.Log
import android.view.View
import app.u_business.R
import app.u_business.databinding.FrHomeBinding
import app.u_business.domain.model.Event
import app.u_business.domain.model.News
import app.u_business.domain.model.Offer
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.ui.news.NewsVM
import app.u_business.presentation.ui.news.renderData
import app.u_business.presentation.ui.profile.special_offers.SpecialOffersAdapter
import app.u_business.presentation.utils.SharedPreferencesHelper
import app.u_business.presentation.utils.navigate
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment(override val layoutId: Int = R.layout.fr_home) : BaseFragment<FrHomeBinding>() {
    private val eventAdapter: EventAdapter by lazy { EventAdapter() }
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter() }
    private val offersAdapter: SpecialOffersAdapter by lazy { SpecialOffersAdapter() }
    private val newsVm by viewModel<NewsVM>()

    private val prefs by inject<SharedPreferencesHelper>()
    private val vm by viewModel<HomeVM>()
    override fun initViews() {
        initRecyclers()
        mockRecyclers()
        vm.state.observe(this){
            Log.d(TAG, "initViews: $it")
        }
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
        newsVm.ldFetchNews.observe(viewLifecycleOwner) {
            renderData(it, null, { newsAdapter.data = it.data.toNewsWithEmpty(2) })
        }
        newsVm.requestNewsList()
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