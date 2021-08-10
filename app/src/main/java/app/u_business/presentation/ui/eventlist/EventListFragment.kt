package app.u_business.presentation.ui.eventlist

import app.u_business.R
import app.u_business.databinding.EventListFragmentBinding
import app.u_business.databinding.ItemSmallEventNewsRBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.ui.eventlist.response.EventItem
import app.u_business.presentation.ui.news.BaseAdapter
import app.u_business.presentation.ui.news.parseDate
import app.u_business.presentation.ui.news.renderData
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventListFragment(override val layoutId: Int = R.layout.event_list_fragment) :
    BaseFragment<EventListFragmentBinding>() {
    //private val adapter: EventListAdapter by lazy { EventListAdapter() }
    private val adapter: BaseAdapter<EventItem?, ItemSmallEventNewsRBinding> by lazy {
        BaseAdapter(
            { li, parent, attach -> ItemSmallEventNewsRBinding.inflate(li, parent, attach) },
            { binding, event ->
                with(binding) {
                    Glide.with(root).load(R.drawable.ic_no_photo_vector)
                        .circleCrop()
                        .into(itemSmallEventNewsImage)

                    itemSmallNewsTypeText.text = event?.category
                    itemSmallNewsTitleText.text = event?.title
                    itemSmallNewsDateText.text = parseDate(event?.date)
                }

            }
        )
    }
    private val eventsVm by viewModel<EventListViewModel>()

    override fun initViews() = initRv()

    private fun initRv() {
        binding.eventListRv.adapter = adapter.setWithBannerTest()
        eventsVm.ldEvents.observe(viewLifecycleOwner) {
            renderData(it, null, { adapter.data = it.data })
        }
        binding.eventSearchTil.setEndIconOnClickListener { eventsVm.requestSearchNews(binding.eventSearchTil.editText?.text.toString()) }
        eventsVm.requestEventList()
    }
}