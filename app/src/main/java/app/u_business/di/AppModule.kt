package app.u_business.di

import app.u_business.data.repo.MainRepoImpl
import app.u_business.data.repo.auth.AuthRepositoryImpl
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.domain.repo.main.MainRepo
import app.u_business.presentation.ui.eventlist.EventListViewModel
import app.u_business.presentation.ui.eventlist.repo.eventlist.EventListRepository
import app.u_business.presentation.ui.eventlist.repo.eventlist.EventListRepositoryImpl
import app.u_business.presentation.ui.home.HomeVM
import app.u_business.presentation.ui.main.MainVM
import app.u_business.presentation.ui.sign_in.AuthVM
import app.u_business.presentation.utils.SharedPreferencesHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    //utils
    single { SharedPreferencesHelper(androidApplication()) }

    //repo
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<EventListRepository> { EventListRepositoryImpl(get()) }
    single<MainRepo> { MainRepoImpl() }

    //vm
    viewModel { MainVM(androidApplication(), get()) }
    viewModel { AuthVM(androidApplication(), get()) }
    viewModel { EventListViewModel(androidApplication(), get()) }
    viewModel { HomeVM(androidApplication(), get()) }
}