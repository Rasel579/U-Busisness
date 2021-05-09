package app.u_business.presentation.ui.welcome

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import app.u_business.R
import app.u_business.databinding.FrWelcomeBinding
import app.u_business.presentation.ui.base.BaseFragment

class WelcomeFragment(override val layoutId: Int = R.layout.fr_welcome) : BaseFragment<FrWelcomeBinding>(), View.OnClickListener {
    private var selectedLanguage: Language = Language.RUSSIAN

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLangPicker()
    }

    private fun initLangPicker() {
        with(binding) {
            welcomeEnPick.setOnClickListener(this@WelcomeFragment)
            welcomeRuPick.setOnClickListener(this@WelcomeFragment)
            welcomeDePick.setOnClickListener(this@WelcomeFragment)
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v?.id) {
                R.id.welcome_en_pick -> {
                    if (selectedLanguage != Language.ENGLISH) {
                        selectedLanguage = Language.ENGLISH
                        setChecked(welcomeEnPick, true)
                        setChecked(welcomeRuPick, false)
                        setChecked(welcomeDePick, false)
                    }
                }
                R.id.welcome_ru_pick -> {
                    if (selectedLanguage != Language.RUSSIAN) {
                        selectedLanguage = Language.RUSSIAN
                        setChecked(welcomeEnPick, false)
                        setChecked(welcomeRuPick, true)
                        setChecked(welcomeDePick, false)
                    }
                }
                R.id.welcome_de_pick -> {
                    if (selectedLanguage != Language.DEUTSCH) {
                        selectedLanguage = Language.DEUTSCH
                        setChecked(welcomeEnPick, false)
                        setChecked(welcomeRuPick, false)
                        setChecked(welcomeDePick, true)
                    }
                }
            }
        }
    }

    private fun setChecked(view: TextView, isChecked: Boolean) {
        if (isChecked)
            view.apply {
                setBackgroundResource(R.drawable.selected_lang_bg)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
        else
            view.apply {
                setBackgroundResource(R.drawable.not_selected_lang_bg)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }
    }
}