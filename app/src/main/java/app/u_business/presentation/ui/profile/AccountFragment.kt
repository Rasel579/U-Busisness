package app.u_business.presentation.ui.profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import app.u_business.R
import app.u_business.databinding.FrAccountBinding
import app.u_business.presentation.ui.base.BaseFragment
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class AccountFragment(override val layoutId: Int = R.layout.fr_account) : BaseFragment<FrAccountBinding>() {

    override fun initViews() {
        initUi()
        initListeners()
//        binding.accountNameEt.setText("Яна Насыбулина")
    }

    private fun initUi() {
        val mask: MaskImpl = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        val watcher: FormatWatcher = MaskFormatWatcher(mask)
        watcher.installOn(binding.accountPhoneEt)

    }

    private fun initListeners() {
        with(binding) {
            accountEditBtn.setOnClickListener { switchOnEditableMode() }
            accountSaveBtn.setOnClickListener { switchOffEditableMode() }
        }
    }

    private fun switchOnEditableMode() {
        with(binding) {
            accountNameEt.isEnabled = true
            accountEmailEt.isEnabled = true
            accountPhoneEt.isEnabled = true
            accountLanguageEt.isEnabled = true
            accountEditMaskImage.isVisible = true
            accountSaveBtn.isVisible = true
            accountEditBtn.isVisible = false
            accountSubscriptionBtn.isVisible = false
        }
    }

    private fun switchOffEditableMode() {
        with(binding) {
            accountNameEt.isEnabled = false
            accountEmailEt.isEnabled = false
            accountPhoneEt.isEnabled = false
            accountLanguageEt.isEnabled = false
            accountEditMaskImage.isVisible = false
            accountSaveBtn.isVisible = false
            accountEditBtn.isVisible = true
            accountSubscriptionBtn.isVisible = true
        }
    }
}