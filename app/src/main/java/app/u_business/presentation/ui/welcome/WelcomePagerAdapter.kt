package app.u_business.presentation.ui.welcome

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

private const val TAB_COUNT = 4

class WelcomePagerAdapter(
    private val context: Context,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = TAB_COUNT

    override fun getItem(position: Int): Fragment =
        when(position) {
            0 -> WelcomeFragment()
            1 -> FirstOnBoardFragment()
            2 -> SecondOnBoardFragment()
            3 -> ThirdOnBoardFragment()
            else -> throw IllegalArgumentException("Position $position is incorrect")
        }
}