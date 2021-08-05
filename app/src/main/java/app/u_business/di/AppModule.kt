package app.u_business.di

import app.u_business.data.repo.auth.AuthRepositoryImpl
import app.u_business.data.repo.news.NewsRepositoryImpl
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.domain.repo.news.NewsRepository
import app.u_business.presentation.ui.main.MainVM
import app.u_business.presentation.ui.news.NewsVM
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
    single<NewsRepository> { NewsRepositoryImpl() }

    //vm
    viewModel { MainVM(androidApplication(), get()) }
    viewModel { AuthVM(androidApplication(), get()) }
    viewModel { NewsVM(androidApplication(), get()) }
}