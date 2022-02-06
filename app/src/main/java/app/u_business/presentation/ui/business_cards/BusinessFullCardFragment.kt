package app.u_business.presentation.ui.business_cards

import android.graphics.Paint
import android.net.Uri
import android.util.Log
import android.view.animation.RotateAnimation
import androidx.core.view.isVisible
import app.u_business.R
import app.u_business.data.network.response.business_card.verified_card_response.ActivatedBusinessCardsItem
import app.u_business.databinding.FrBusinessFullCardBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.showAlertDialog
import com.bumptech.glide.Glide
import org.koin.android.ext.android.inject
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


class BusinessFullCardFragment(override val layoutId: Int = R.layout.fr_business_full_card) :
    BaseFragment<FrBusinessFullCardBinding>() {
    private val vm by inject<BusinessCardsVM>()
    private var isExpanded = false
    private var data: ActivatedBusinessCardsItem? = null
    override fun initViews() {
        data = arguments?.getParcelable("Card")
        initUi()
        mock()
        initListeners()
        initData(data)
        initViewModel()
    }

    override fun initViewModel() {
        vm.stateLiveData.observe(viewLifecycleOwner) {
            showAlertDialog(it as String, it as String)
        }
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
            .load(
                if (data?.avatarurl != null) {
                    Uri.parse(data?.avatarurl)
                } else R.drawable.test_avatar
            )
            .circleCrop()
            .into(binding.businessCardAvatarImage)
    }

    private fun initListeners() {
        with(binding) {
            businessCardMoreBtn.setOnClickListener { showOrUnshowExtraInfo() }
            businessCardAddBtn.setOnClickListener {
                Log.e("check_add_btn", data?.userId.toString())
                data?.userId?.let { id -> vm.addFavoriteCard(id) }
            }
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


    private fun initData(businessCardData: ActivatedBusinessCardsItem?) = with(binding) {
        businessCardData?.let {
            businessCardNameText.text = it.user
            if (it.status.toBoolean()) {
                businessCardStatusText.text = getString(R.string.status_validate_text)
            } else {
                businessCardStatusText.text = getString(R.string.satus_invalidate_text)
                businessCardStatusImage.setImageResource(R.drawable.not_selected_lang_bg)
            }
            businessCardCompanyNameEt.setText(it.company)
            Glide.with(requireContext())
                .load(
                    it.avatarurl ?: R.drawable.ic_menu_camera
                )
                .circleCrop()
                .into(binding.businessCardAvatarImage)
            businessCardAddressEt.setText(it.industry)
            businessCardCompanyNameEt.setText(it.company)
            businessCardCountryEt.setText(it.company)
            businessCardTagsEt.setText(it.industry)
        }
    }


}