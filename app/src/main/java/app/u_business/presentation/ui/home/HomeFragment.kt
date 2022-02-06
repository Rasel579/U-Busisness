package app.u_business.presentation.ui.home

import android.util.Log
import android.view.View
import app.u_business.R
import app.u_business.databinding.FrHomeBinding
import app.u_business.databinding.ItemEventBinding
import app.u_business.databinding.ItemNewsBinding
import app.u_business.databinding.ItemOfferBinding
import app.u_business.domain.model.Event
import app.u_business.domain.model.News
import app.u_business.domain.model.Offer
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.ui.news.BaseAdapter
import app.u_business.presentation.ui.news.renderData
import app.u_business.presentation.utils.SharedPreferencesHelper
import app.u_business.presentation.utils.navigate
import com.bumptech.glide.Glide
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment(override val layoutId: Int = R.layout.fr_home) : BaseFragment<FrHomeBinding>() {
    //private val eventAdapter: EventAdapter by lazy { EventAdapter() }
    private val eventAdapter: BaseAdapter<Event?, ItemEventBinding> by lazy {
        BaseAdapter(
            { li, parent, attach -> ItemEventBinding.inflate(li, parent, attach) },
            { binding, event ->
                with(binding) {
                    Glide.with(binding.root)
                        .load(R.drawable.test_image)
                        .fitCenter()
                        .into(itemEventImage)

                    itemEventTitleText.text = event?.title
                    itemEventDateText.text = event?.date
                }
            }
        )
    }

    //private val newsAdapter: NewsAdapter by lazy { NewsAdapter() }
    private val newsAdapter: BaseAdapter<News?, ItemNewsBinding> by lazy {
        BaseAdapter(
            { li, parent, attach -> ItemNewsBinding.inflate(li, parent, attach) },
            { binding, model ->
                Glide.with(binding.root).load(R.drawable.test_news).fitCenter()
                    .into(binding.itemNewsImage)
                binding.itemNewsTitleText.text = model?.title
                binding.itemNewsDateText.text = model?.date
            }
        )
    }

    //private val offersAdapter: SpecialOffersAdapter by lazy { SpecialOffersAdapter() }
    private val offersAdapter: BaseAdapter<Offer?, ItemOfferBinding> by lazy {
        BaseAdapter(
            { li, parent, attach -> ItemOfferBinding.inflate(li, parent, attach) },
            { binding, offer ->
                binding.discount = offer?.discount
                binding.goods = offer?.goods
            }
        )
    }

    private val prefs by inject<SharedPreferencesHelper>()
    private val vm by viewModel<HomeVM>()
    override fun initViews() {

        initRecyclers()
        
        vm.state.observe(this) {
            renderData(it, null, {
                newsAdapter.data = it.data.news.toNewsWithEmpty(2)
                eventAdapter.data = it.data.events.subList(
                    0, if (it.data.events.size > 2) 2 else it.data.events.size
                ).map { it?.toEven() }.toMutableList().apply { add(null) }
                offersAdapter.data = it.data.offers.toOffers()
            })
        }
        vm.requestHomeData()
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
}