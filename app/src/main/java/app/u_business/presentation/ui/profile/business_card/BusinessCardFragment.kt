package app.u_business.presentation.ui.profile.business_card

import android.graphics.Paint
import android.view.animation.RotateAnimation
import androidx.core.view.isVisible
import app.u_business.R
import app.u_business.databinding.FrBusinessCardBinding
import app.u_business.presentation.ui.base.BaseFragment
import com.bumptech.glide.Glide
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


class BusinessCardFragment(override val layoutId: Int = R.layout.fr_business_card) :
    BaseFragment<FrBusinessCardBinding>() {

    private var isExpanded = false

    override fun initViews() {
        initUi()
        mock()
        initListeners()
    }

    private fun initUi() {
        with(binding) {
            val mask: MaskImpl = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
            val watcher: FormatWatcher = MaskFormatWatcher(mask)
            watcher.installOn(businessCardPhoneEt)

            businessCardMoreText.paintFlags =
                businessCardMoreText.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }

    private fun mock() {
        Glide.with(requireContext())
            .load(R.drawable.test_avatar)
            .circleCrop()
            .into(binding.businessCardAvatarImage)
    }

    private fun initListeners() {
        with(binding) {
            businessCardMoreWrapper.setOnClickListener { showOrUnshowExtraInfo() }
            businessCardEditBtn.setOnClickListener { switchOnEditableMode() }
            businessCardSaveBtn.setOnClickListener { switchOffEditableMode() }
        }
    }

    private fun showOrUnshowExtraInfo() {
        with(binding) {
            if (isExpanded) {
                rotateArrowImage(180f, 0f)
                businessCardMoreInfoWrapper.isVisible = false
                businessCardMoreText.text = getString(R.string.business_card_more)
            } else {
                rotateArrowImage(0f, 180f)
                businessCardMoreInfoWrapper.isVisible = true
                businessCardMoreText.text = getString(R.string.business_card_roll_up)
            }
            isExpanded = !isExpanded
        }
    }

    private fun switchOnEditableMode() {
        with(binding) {
            businessCardEditBtn.isVisible = false
            businessCardSaveBtn.isVisible = true

            businessCardIndustryEt.isEnabled = true
            businessCardCompanyNameEt.isEnabled = true
            businessCardPhoneEt.isEnabled = true
            businessCardCountryEt.isEnabled = true
            businessCardAddressEt.isEnabled = true
            businessCardTagsEt.isEnabled = true

            businessCardCompanyLogoMask.isVisible = true
        }
    }

    private fun switchOffEditableMode() {
        with(binding) {
            businessCardEditBtn.isVisible = true
            businessCardSaveBtn.isVisible = false

            businessCardIndustryEt.isEnabled = false
            businessCardCompanyNameEt.isEnabled = false
            businessCardPhoneEt.isEnabled = false
            businessCardCountryEt.isEnabled = false
            businessCardAddressEt.isEnabled = false
            businessCardTagsEt.isEnabled = false

            businessCardCompanyLogoMask.isVisible = false
        }
    }

    private fun rotateArrowImage(fromRotation: Float, toRotation: Float) {
        with(binding) {
            val rotateAnim = RotateAnimation(
                fromRotation,
                toRotation,
                businessCardMoreBtn.width / 2f,
                businessCardMoreBtn.height / 2f
            )

            rotateAnim.duration = 300
            rotateAnim.fillAfter = true

            businessCardMoreBtn.startAnimation(rotateAnim)
        }

    }
}