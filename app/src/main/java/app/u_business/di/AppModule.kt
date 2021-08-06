package app.u_business.di

import app.u_business.data.repo.auth.AuthRepositoryImpl
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.presentation.ui.eventlist.EventListViewModel
import app.u_business.presentation.ui.eventlist.repo.eventlist.EventListRepository
import app.u_business.presentation.ui.eventlist.repo.eventlist.EventListRepositoryImpl
import app.u_business.presentation.ui.main.MainVM
import app.u_business.presentation.ui.sign_in.AuthVM
import app.u_business.presentation.utils.SharedPreferencesHelper
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    //utils
    single { SharedPreferencesHelper(androidApplication()) }

    //repo
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<EventListRepository> { EventListRepositoryImpl() }

    //vm
    viewModel { MainVM(androidApplication(), get()) }
    viewModel { AuthVM(androidApplication(), get()) }
    viewModel { EventListViewModel(androidApplication(), get()) }
}

private inline fun <reified T> createWebService(
    httpClient: OkHttpClient,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(httpClient)
        .baseUrl(baseUrl)
        .build()

    return retrofit.create(T::class.java)
}