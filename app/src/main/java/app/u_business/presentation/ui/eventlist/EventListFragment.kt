package app.u_business.presentation.ui.eventlist

import app.u_business.R
import app.u_business.databinding.EventListFragmentBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.ui.eventlist.adapter.EventListAdapter
import app.u_business.presentation.ui.news.renderData
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventListFragment(override val layoutId: Int = R.layout.event_list_fragment) :
    BaseFragment<EventListFragmentBinding>() {
    private val adapter: EventListAdapter by lazy { EventListAdapter() }
    private val eventsVm by viewModel<EventListViewModel>()

    override fun initViews() = initRv()

    private fun initRv() {
        binding.eventListRv.adapter = adapter
        eventsVm.ldEvents.observe(viewLifecycleOwner) {
            renderData(it, null, { adapter.data = it.data })
        }
        eventsVm.requestEventList()
    }
}