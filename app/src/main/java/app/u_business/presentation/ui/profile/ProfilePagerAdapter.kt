package app.u_business.presentation.ui.profile

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import app.u_business.R

private val TAB_TITLES = listOf(
    R.string.profile_account,
    R.string.profile_business_card,
    R.string.profile_special_offers,
)

class ProfilePagerAdapter(
    private val context: Context,
    private val fragments: List<Fragment>,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getPageTitle(position: Int): CharSequence =
        context.getString(TAB_TITLES[position])


    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment =
        fragments[position]
}