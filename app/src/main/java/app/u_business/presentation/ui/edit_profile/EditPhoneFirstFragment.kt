package app.u_business.presentation.ui.edit_profile

import app.u_business.R
import app.u_business.databinding.FrEditPhoneFirstBinding
import app.u_business.presentation.ui.base.BaseFragment
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class EditPhoneFirstFragment(override val layoutId: Int = R.layout.fr_edit_phone_first) :
    BaseFragment<FrEditPhoneFirstBinding>() {

    override fun initViews() {
        initMask()
    }

    private fun initMask() {
        val mask: MaskImpl = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        val watcher: FormatWatcher = MaskFormatWatcher(mask)
        watcher.installOn(binding.editPhoneNumberEt)
    }
}