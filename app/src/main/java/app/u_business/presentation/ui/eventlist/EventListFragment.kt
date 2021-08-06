package app.u_business.presentation.ui.eventlist

import app.u_business.R
import app.u_business.databinding.EventListFragmentBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.ui.eventlist.adapter.EventListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventListFragment(override val layoutId: Int = R.layout.event_list_fragment) :
    BaseFragment<EventListFragmentBinding>()
//    OnItemEventClickListener
{

    private val vm by viewModel<EventListViewModel>()

    private val adapter: EventListAdapter by lazy { EventListAdapter() }

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

    private fun setObservers() {
        // observe status
        vm.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                 is EventListState.Success -> {
                     adapter.data = state.serverResponseData ?: listOf()
//                    binding.progressBar?.visibility = View.INVISIBLE
                }
                is EventListState.Loading -> {
//                    binding.progressBar?.visibility = View.VISIBLE
                }
                is EventListState.Error -> {
//                    binding.progressBar?.visibility = View.INVISIBLE
                }
            }
        })
    }
}