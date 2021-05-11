package app.u_business.presentation.ui.welcome

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class WelcomePagerAdapter(
    private val context: Context,
    private val fragments: List<Fragment>,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment =
        fragments[position]
}