package app.u_business.presentation.ui.profile.business_card

import android.graphics.Paint
import android.view.animation.RotateAnimation
import androidx.core.view.isVisible
import app.u_business.R
import app.u_business.databinding.FrBusinessCardBinding
import app.u_business.presentation.ui.base.BaseFragment
import com.bumptech.glide.Glide


class BusinessCardFragment(override val layoutId: Int = R.layout.fr_business_card) :
    BaseFragment<FrBusinessCardBinding>() {

    private var isExpanded = false

    override fun initViews() {
        initListeners()
        with(binding) {
            Glide.with(requireContext())
                .load(R.drawable.test_avatar)
                .circleCrop()
                .into(businessCardAvatarImage)

            businessCardMoreText.paintFlags =
                businessCardMoreText.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }

    private fun initListeners() {
        with(binding) {
            businessCardMoreWrapper.setOnClickListener {
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
            rotateAnim.fillAfter = true // Must be true or the animation will reset


            businessCardMoreBtn.startAnimation(rotateAnim)
        }

    }
}