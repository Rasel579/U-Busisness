package app.u_business.di


import app.u_business.data.repo.EventsRepoImpl
import app.u_business.data.repo.MainRepoImpl
import app.u_business.data.repo.auth.AuthRepositoryImpl
import app.u_business.data.repo.business_card.BusinessCardRepoImpl
import app.u_business.data.repo.news.NewsRepositoryImpl
import app.u_business.domain.repo.EventsRepository
import app.u_business.domain.repo.auth.AuthRepository
import app.u_business.domain.repo.business_card.BusinessCardRepo
import app.u_business.domain.repo.main.MainRepo
import app.u_business.domain.repo.news.NewsRepository
import app.u_business.presentation.ui.business_cards.BusinessCardsVM
import app.u_business.presentation.ui.eventlist.EventListViewModel
import app.u_business.presentation.ui.home.HomeVM
import app.u_business.presentation.ui.main.MainVM
import app.u_business.presentation.ui.news.NewsVM
import app.u_business.presentation.ui.profile.business_card.BusinessCardVM
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
    single<NewsRepository> { NewsRepositoryImpl() }
    single<BusinessCardRepo> { BusinessCardRepoImpl() }
    single<EventsRepository> { EventsRepoImpl() }
    single<MainRepo> { MainRepoImpl() }


    //vm
    viewModel { MainVM(androidApplication(), get(), get()) }
    viewModel { AuthVM(androidApplication(), get(), get()) }
    viewModel { NewsVM(androidApplication(), get()) }
    viewModel { EventListViewModel(androidApplication(), get()) }
    viewModel { HomeVM(androidApplication(), get()) }
    viewModel { BusinessCardVM(androidApplication(), get(), get()) }
    viewModel { BusinessCardsVM(androidApplication(), get(), get()) }
}

//private inline fun <reified T> createWebService(
//    httpClient: OkHttpClient,
//    baseUrl: String
//): T {
//    val retrofit = Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create(Gson()))
//        .client(httpClient)
//        .baseUrl(baseUrl)
//        .build()
//
//    return retrofit.create(T::class.java)
//}