package app.u_business.presentation.ui.eventlist

import android.view.View
import app.u_business.R
import app.u_business.databinding.EventListFragmentBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.ui.eventlist.`interface`.OnItemEventClickListener
import app.u_business.presentation.ui.eventlist.adapter.EventListAdapter
import app.u_business.presentation.ui.eventlist.model.EventList
import app.u_business.presentation.utils.SharedPreferencesHelper
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventListFragment(override val layoutId: Int = R.layout.event_list_fragment) :
    BaseFragment<EventListFragmentBinding>(),
    OnItemEventClickListener {

    private val vm by viewModel<EventListViewModel>()

    private var UserID: String = "26"

    private val sharedPreferencesHelper by inject<SharedPreferencesHelper>()

    private val adapter: EventListAdapter by lazy { EventListAdapter(this) }

    override fun initViews() {
        initRv()
    }

    override fun initViewModel() {
        setObservers()
    }

    private fun initRv() {
        binding.eventListRv.adapter = adapter
    }

    companion object {
        fun newInstance() = EventListFragment()
    }

    override fun onItemViewClick(eventList: EventList) {
        TODO("Not yet implemented")
    }

    override fun onStart() {
        super.onStart()
        vm.loadeventListFromApi(UserID)

    }

    private fun setObservers() {
        // observe movies data
        vm.eventList.observe(viewLifecycleOwner, {
            val eventList = it ?: return@observe
            eventList.let {
                adapter.data
//                adapter.setEventListData(eventList)
                adapter.notifyDataSetChanged()
            }
        })

        // observe status
        vm.state.observe(viewLifecycleOwner, { status ->
            when (status) {
                is State.Init, is State.Success -> {
//                    binding.progressBar?.visibility = View.INVISIBLE
                }
                is State.Loading -> {
//                    binding.progressBar?.visibility = View.VISIBLE
                }
                is State.Error -> {
//                    binding.progressBar?.visibility = View.INVISIBLE
                }
            }
        })
    }
}