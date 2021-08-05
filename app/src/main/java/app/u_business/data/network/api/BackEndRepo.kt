package app.u_business.data.network.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackEndRepo {
    val api : ServiceApi by lazy {
       val adapter = Retrofit.Builder()
           .baseUrl(ApiUtils.baseUrl)
           .addConverterFactory(GsonConverterFactory.create(Gson()))
           .client(ApiUtils.getOkHttpClient())
           .build()
        adapter.create(ServiceApi::class.java)
    }
}