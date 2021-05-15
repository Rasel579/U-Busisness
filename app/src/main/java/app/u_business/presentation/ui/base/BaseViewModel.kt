package app.u_business.presentation.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.koin.core.KoinComponent

open class BaseViewModel(app: Application): AndroidViewModel(app), KoinComponent