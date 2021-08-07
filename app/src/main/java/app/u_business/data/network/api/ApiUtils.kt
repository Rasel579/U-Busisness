package app.u_business.data.network.api

import app.u_business.presentation.utils.SharedPreferencesHelper
import okhttp3.OkHttpClient
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.http.Body
import java.util.concurrent.TimeUnit

object ApiUtils {
    val sharedPreferencesHelper = object : KoinComponent {
        val im: SharedPreferencesHelper by inject()
    }.im
    val baseUrl = "https://ubusiness-ithub.ru/"
    private val duration = 2000L
    fun getOkHttpClient() : OkHttpClient {
        val httpClient = OkHttpClient.Builder().apply {
            connectTimeout(duration, TimeUnit.MILLISECONDS)
            readTimeout(duration, TimeUnit.MILLISECONDS)
            writeTimeout(duration, TimeUnit.MILLISECONDS)
        }
        httpClient.addInterceptor { chain ->
            val originReq = chain.request()
            val request = originReq.newBuilder()
//                .method(originReq.method(), originReq.body())
                .addHeader("Authorization", "Bearer " + sharedPreferencesHelper.accessToken)
                .build()
            chain.proceed(request)
        }
        return httpClient.build()
    }
}