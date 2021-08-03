package app.u_business.presentation.ui.profile.business_card

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.animation.RotateAnimation
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import app.u_business.R
import app.u_business.data.network.query.cards.BusinessCardBody
import app.u_business.data.network.response.business_card.card_response.BusinessCardResponse
import app.u_business.databinding.FrBusinessCardBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.showAlertDialog
import com.bumptech.glide.Glide
import org.koin.android.ext.android.inject
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


class BusinessCardFragment(override val layoutId: Int = R.layout.fr_business_card) :
    BaseFragment<FrBusinessCardBinding>() {
    private val vm by inject<BusinessCardVM>()
    private var isExpanded = false
    private var resImage: String? = null
    private var uriImage: Uri? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPermissions()
    }

    override fun initViews() {
        initUi()
        mock()
        initListeners()
        initEventAction()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initPermissions() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        }
    }


    private fun initUi() {
        with(binding) {

            val mask: MaskImpl = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
            val watcher: FormatWatcher = MaskFormatWatcher(mask)
            watcher.installOn(businessCardPhoneEt)

            businessCardMoreText.paintFlags =
                businessCardMoreText.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            //fetch business card
            vm.fetchBusinessCard()
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

    private fun chooseImageCompany() {
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(intent, RESULT_LOAD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            uriImage = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = uriImage?.let {
                context?.contentResolver?.query(
                    it,
                    filePathColumn, null, null, null
                )
            }
            cursor?.moveToFirst()
            val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
            resImage = columnIndex?.let { cursor?.getString(it) }
            cursor?.close()
            binding.businessCardCompanyLogoImage.setImageBitmap(BitmapFactory.decodeFile(resImage))
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
            businessCardCompanyLogoImage.setOnClickListener { chooseImageCompany() }
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
            vm.postEditCard(
                BusinessCardBody(
                    businessCardNameText.text as String,
                    businessCardCountryEt.text.toString(),
                    businessCardAddressEt.text.toString(),
                    businessCardIndustryEt.text.toString(),
                    businessCardTagsEt.text.toString(),
                    0
                ), resImage
            )

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

    private fun initEventAction() {
        vm.fetchCardsEvent.observe(viewLifecycleOwner) { event ->
            when (event.action) {
                is CardFetchAction.Success -> {
                    initData(event.action.businessCardResponseItem)
                }
                is CardFetchAction.Error -> {
                    event.action.message?.let { showAlertDialog(it, event.action.message) }
                }
                is CardFetchAction.SuccessEdit -> {
                    event.action.message?.let { showAlertDialog(event.action.message, it) }
                }
            }

        }
    }

    private fun initData(businessCardData: BusinessCardResponse?) = with(binding) {
        businessCardData?.first().let {
            businessCardNameText.text = it?.name
            if (it?.status.toBoolean()) {
                businessCardStatusText.text = getString(R.string.status_validate_text)
            } else {
                businessCardStatusText.text = getString(R.string.satus_invalidate_text)
                businessCardStatusImage.setImageResource(R.drawable.not_selected_lang_bg)
            }
            businessCardCompanyNameEt.setText(it?.company)
            Glide.with(requireContext())
                .load(
                    it?.urlLogo ?: R.drawable.ic_menu_camera
                )
                .circleCrop()
                .into(binding.businessCardAvatarImage)
            businessCardAddressEt.setText(it?.address)
            businessCardCompanyNameEt.setText(it?.company)
            businessCardCountryEt.setText(it?.country)
            businessCardTagsEt.setText(it?.tags)
        }
    }

    companion object {
        private const val RESULT_LOAD_IMAGE = 1
    }
}