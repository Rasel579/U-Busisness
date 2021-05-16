package app.u_business.presentation.ui.profile.special_offers

import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import app.u_business.R
import app.u_business.databinding.FrEditOfferBinding
import app.u_business.presentation.ui.base.BaseFragment
import ru.tinkoff.decoro.Mask
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


class EditOfferFragment(override val layoutId: Int = R.layout.fr_edit_offer) :
    BaseFragment<FrEditOfferBinding>() {

    override fun initViews() {
        initListeners()
        initMask()
    }

    private fun initListeners() {
        binding.editOfferSaveBtn.setOnClickListener { findNavController().navigateUp() }
    }

    private fun initMask() {
        val slots = UnderscoreDigitSlotsParser().parseSlots("__/__/____")
        val mask = MaskImpl.createTerminated(slots)
        val watcher: FormatWatcher = MaskFormatWatcher(mask)
        watcher.installOn(binding.editOfferValidityEt)
    }
}