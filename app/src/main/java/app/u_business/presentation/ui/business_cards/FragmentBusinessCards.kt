package app.u_business.presentation.ui.business_cards

import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuInflater
import app.u_business.R
import app.u_business.data.network.response.business_card.verified_card_response.ActivatedBusinessCardsItem
import app.u_business.data.network.response.business_card.verified_card_response.FetchActivatedBusinessCards
import app.u_business.databinding.FrBussinessCardsBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.navigate
import app.u_business.presentation.utils.showAlertDialog
import org.koin.android.ext.android.inject

class FragmentBusinessCards(override val layoutId: Int = R.layout.fr_bussiness_cards) :
    BaseFragment<FrBussinessCardsBinding>() {
    private val vm by inject<BusinessCardsVM>()
    private var data: FetchActivatedBusinessCards? = null
    private var isGetAll = true


    override fun initViews() {
        initVM()
        initListeners()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.business_card_toolbar_menu, menu)
    }

    private fun initRv() {
        binding.businessCardsRv.adapter = BusinessCardsAdapter(data, object : OnLongClickListener {
            override fun onItemLongClickListener(businessCardResponseItem: ActivatedBusinessCardsItem) {
                val bundle = Bundle().apply {
                    putParcelable("Card", businessCardResponseItem)
                }
                navigate(R.id.nav_business_card_full_fragment, bundle)
            }
        })
    }


    private fun initVM() {
        vm.stateLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is FetchActivatedBusinessCards -> {
                    data = it
                    initRv()
                }
                is String? -> showAlertDialog(it, it)
            }
        })
        vm.getBusinessCards()
    }

    private fun initListeners() {
        binding.offersSearchEt.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                vm.searchBusinessCards(binding.offersSearchEt.text.toString())
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
        binding.favoriteCards.setOnClickListener {
            isGetAll = !isGetAll
            if (isGetAll) {
                binding.allCards.isChecked = true
                binding.favoriteCards.isChecked = false
            } else {
                binding.favoriteCards.isChecked = true
                binding.allCards.isChecked = false
                vm.getFavoritesCards()
            }
        }
        binding.allCards.setOnClickListener {
            isGetAll = !isGetAll
            if (isGetAll) {
                binding.allCards.isChecked = true
                binding.favoriteCards.isChecked = false
                vm.getBusinessCards()

            } else {
                binding.favoriteCards.isChecked = true
                binding.allCards.isChecked = false
                vm.getFavoritesCards()
            }
        }


    }
}
