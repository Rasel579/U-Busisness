package app.u_business.presentation.ui.profile.business_card

import android.graphics.Paint
import app.u_business.R
import app.u_business.databinding.FrBusinessCardBinding
import app.u_business.presentation.ui.base.BaseFragment
import com.bumptech.glide.Glide

class BusinessCardFragment(override val layoutId: Int = R.layout.fr_business_card) :
    BaseFragment<FrBusinessCardBinding>() {

    override fun initViews() {
        with(binding) {
            Glide.with(requireContext())
                .load(R.drawable.test_avatar)
                .circleCrop()
                .into(businessCardAvatarImage)

            businessCardMoreText.paintFlags =
                businessCardMoreText.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }
}