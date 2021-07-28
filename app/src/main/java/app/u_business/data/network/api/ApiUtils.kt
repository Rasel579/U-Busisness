package app.u_business.data.network.api

import okhttp3.OkHttpClient
import retrofit2.http.Body
import java.util.concurrent.TimeUnit

object ApiUtils {
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
                .method(originReq.method(), originReq.body())
                .build()
            chain.proceed(request)
        }
        return httpClient.build()
    }
}