package app.u_business.presentation.ui.welcome

import app.u_business.R
import app.u_business.databinding.FrThirdOnBoardBinding
import app.u_business.presentation.ui.base.BaseFragment
import app.u_business.presentation.utils.navigate

class ThirdOnBoardFragment(override val layoutId: Int = R.layout.fr_third_on_board) : BaseFragment<FrThirdOnBoardBinding>() {

    override fun initViews() {
        binding.next.setOnClickListener { navigate(R.id.auth) }
    }
}